package org.tdd.example.app.customer.dao.provider;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.SelectResults;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;
import org.mockito.verification.VerificationMode;
import org.tdd.example.app.customer.dao.DataAccessException;
import org.tdd.example.app.customer.domain.Customer;
import org.tdd.example.core.util.CollectionUtils;

/**
 * The GemFireCustomerDaoTest class...
 *
 * @author John Blum
 * @see org.junit.Test
 * @see org.mockito.Mockito
 * @see org.tdd.example.app.customer.dao.provider.GemFireCustomerDao
 * @see org.tdd.example.app.customer.domain.Customer
 * @see com.gemstone.gemfire.cache.Region
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class GemFireCustomerDaoTest {

  private static final AtomicLong ID_SEQUENCE = new AtomicLong(0);

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  protected static Customer newCustomer(final String firstName, final String lastName) {
    return newCustomer(ID_SEQUENCE.incrementAndGet(), firstName, lastName);
  }

  protected static Customer newCustomer(final Long id, final String firstName, final String lastName) {
    Customer customer = new Customer(firstName, lastName);
    customer.setId(id);
    return customer;
  }

  @Test
  public void findById() throws Exception {
    Customer jonDoe = newCustomer("Jon", "Doe");

    Region<Long, Customer> mockRegion = mock(Region.class, "testFindById.MockRegion");

    SelectResults mockSelectResults = mock(SelectResults.class, "testFindById.MockSelectResults");

    List<Customer> spyResults = spy(CollectionUtils.asList(jonDoe));

    when(mockRegion.query(eq(String.format("id = %1$d", jonDoe.getId())))).thenReturn(mockSelectResults);
    when(mockSelectResults.asList()).thenReturn(spyResults);

    GemFireCustomerDao dao = new GemFireCustomerDao(mockRegion);

    assertThat(dao.getCustomersRegion(), is(sameInstance(mockRegion)));

    Customer actualCustomer = dao.findById(jonDoe.getId());

    assertThat(actualCustomer, is(equalTo(jonDoe)));

    InOrder inOrderVerifier = inOrder(mockRegion, mockSelectResults, spyResults);

    inOrderVerifier.verify(mockRegion, times(1)).query(eq(String.format("id = %1$d", jonDoe.getId())));
    inOrderVerifier.verify(mockSelectResults, times(1)).asList();
    inOrderVerifier.verify(spyResults, times(1)).get(eq(0));
  }

  @Test
  public void findByIdThrowsOnNoMatches() throws Exception {
    Customer pieDoe = newCustomer(42l, "Pie", "Doe");

    Region<Long, Customer> mockRegion = mock(Region.class, "testFindByIdThrowsOnNoMatches.MockRegion");

    SelectResults mockEmptySelectResults = mock(SelectResults.class,
      "testFindByIdThrowsOnNoMatches.MockEmptySelectResults");

    SelectResults mockPieDoeSelectResults = mock(SelectResults.class,
      "testFindByIdThrowsOnNoMatches.MockPieDoeSelectResults");

    when(mockRegion.query(eq(String.format("id = %1$d", pieDoe.getId())))).thenReturn(mockPieDoeSelectResults);
    when(mockPieDoeSelectResults.asList()).thenReturn(Arrays.asList(pieDoe));
    when(mockRegion.query(eq("id = 24"))).thenReturn(mockEmptySelectResults);
    when(mockEmptySelectResults.asList()).thenReturn(Collections.emptyList());

    GemFireCustomerDao dao = new GemFireCustomerDao(mockRegion);

    assertThat(dao.getCustomersRegion(), is(sameInstance(mockRegion)));

    expectedException.expect(DataAccessException.class);
    expectedException.expectCause(is(nullValue(Throwable.class)));
    expectedException.expectMessage(is(equalTo("expected 1 Customer with ID (24); but found (0) matches")));

    try {
      dao.findById(24l);
    }
    finally {
      verify(mockRegion, times(1)).query("id = 24");
      verify(mockRegion, never()).query(String.format("id = %1$d", pieDoe.getId()));
      verify(mockEmptySelectResults, times(1)).asList();
      verify(mockPieDoeSelectResults, never()).asList();
    }
  }

  @Test
  public void findByIdThrowsOnMultipleMatches() throws Exception {
    Customer cookieDoe = newCustomer(69l, "Cookie", "Doe");
    Customer sourDoe = newCustomer(69l, "Sour", "Doe");

    Region<Long, Customer> mockRegion = mock(Region.class, "testFindByIdThrowsOnMultipleMatches.MockRegion");

    SelectResults mockSelectResults = mock(SelectResults.class, "testFindByIdThrowsOnMultipleMatches.MockSelectResults");

    when(mockRegion.query(eq(String.format("id = %1$d", cookieDoe.getId())))).thenReturn(mockSelectResults);
    when(mockSelectResults.asList()).thenReturn(Arrays.asList(cookieDoe, sourDoe));

    GemFireCustomerDao dao = new GemFireCustomerDao(mockRegion);

    assertThat(dao.getCustomersRegion(), is(sameInstance(mockRegion)));

    expectedException.expect(DataAccessException.class);
    expectedException.expectCause(is(nullValue(Throwable.class)));
    expectedException.expectMessage(is(equalTo(String.format(
      "expected 1 Customer with ID (%1$d); but found (2) matches", cookieDoe.getId()))));

    try {
      dao.findById(69l);
    }
    finally {
      verify(mockRegion, times(1)).query(String.format("id = %1$d", sourDoe.getId()));
      verify(mockSelectResults, times(1)).asList();
    }
  }

  @Test
  public void findByIdThrowsQueryInvocationTargetException() throws Exception {
    Region<Long, Customer> mockRegion = mock(Region.class, "testFindByIdThrowsQueryInvocationTargetException.MockRegion");

    when(mockRegion.query(anyString())).thenThrow(new QueryInvocationTargetException("TEST"));

    expectedException.expect(DataAccessException.class);
    expectedException.expectCause(CoreMatchers.isA(QueryInvocationTargetException.class));
    expectedException.expectMessage(is(equalTo("failed to find Customer by ID (1)")));

    GemFireCustomerDao dao = new GemFireCustomerDao(mockRegion);

    assertThat(dao.getCustomersRegion(), is(sameInstance(mockRegion)));

    try {
      dao.findById(1l);
    }
    finally {
      verify(mockRegion, times(1)).query(anyString());
    }
  }

}
