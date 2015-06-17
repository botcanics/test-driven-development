package org.tdd.example.app.customer.dao.provider;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicLong;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.DataPolicy;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionFactory;
import com.gemstone.gemfire.cache.query.QueryInvalidException;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.tdd.example.app.customer.dao.DataAccessException;
import org.tdd.example.app.customer.domain.Customer;

/**
 * The GemFireCustomerDaoIntegrationTest class...
 *
 * @author John Blum
 * @see org.tdd.example.app.customer.domain.Customer
 * @see org.tdd.example.app.customer.dao.provider.GemFireCustomerDao
 * @see com.gemstone.gemfire.cache.Cache
 * @see com.gemstone.gemfire.cache.Region
 * @since 1.0.0
 */
public class GemFireCustomerDaoIntegrationTest {

  private static final AtomicLong ID_SEQUENCE = new AtomicLong(0);

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private static GemFireCustomerDao customerDao;

  private static Region<Long, Customer> customers;

  @BeforeClass
  public static void setupTestSuite() {
    Cache gemfireCache = new CacheFactory()
      .set("name", GemFireCustomerDaoIntegrationTest.class.getSimpleName())
      .set("log-level", "config")
      .set("mcast-port", "0")
      .create();

    RegionFactory<Long, Customer> customerRegionFactory = gemfireCache.createRegionFactory();

    customerRegionFactory.setDataPolicy(DataPolicy.REPLICATE);
    customerRegionFactory.setKeyConstraint(Long.class);
    customerRegionFactory.setValueConstraint(Customer.class);

    customers = customerRegionFactory.create("Customers");

    customerDao = new GemFireCustomerDao(customers);
  }

  @AfterClass
  public static void tearDownTestSuite() {
    CacheFactory.getAnyInstance().close();
    customerDao = null;
  }

  @Before
  public void setup() {
    customers.removeAll(customers.keySet());

    assertThat(customerDao.getCustomersRegion(), is(sameInstance(customers)));
    assertThat(customers.isEmpty(), is(equalTo(true)));
  }

  protected static Customer newCustomer(final String firstName, final String lastName) {
    return newCustomer(ID_SEQUENCE.incrementAndGet(), firstName, lastName);
  }

  protected static Customer newCustomer(final Long id, final String firstName, final String lastName) {
    Customer customer = new Customer(firstName, lastName);
    customer.setId(id);
    return customer;
  }

  protected static Customer put(Region<Long, Customer> customers, Customer customer) {
    return put(customers, customer.getId(), customer);
  }

  protected static Customer put(Region<Long, Customer> customers, Long key, Customer customer) {
    customers.put(key, customer);
    return customer;
  }

  @Test
  public void findById() {
    Customer jonDoe = put(customers, newCustomer("Jon", "Doe"));

    assertThat(customers.size(), is(equalTo(1)));
    assertThat(customers.get(jonDoe.getId()), is(equalTo(jonDoe)));

    Customer actualCustomer = customerDao.findById(jonDoe.getId());

    assertThat(actualCustomer, is(equalTo(jonDoe)));
  }

  @Test
  public void findByIdThrowsOnNoMatches() {
    assertThat(customers.isEmpty(), is(equalTo(true)));

    expectedException.expect(DataAccessException.class);
    expectedException.expectCause(is(nullValue(Throwable.class)));
    expectedException.expectMessage(is(equalTo("expected 1 Customer with ID (2); but found (0) matches")));

    customerDao.findById(2l);
  }

  @Test
  public void findByIdThrowsOnMultipleMatches() {
    Customer cookieDoe = put(customers, 1l, newCustomer(4l, "Cookie", "Doe"));
    Customer pieDoe = put(customers, 2l, newCustomer(4l, "Pie", "Doe"));

    assertThat(customers.size(), is(equalTo(2)));
    assertThat(customers.get(1l), is(equalTo(cookieDoe)));
    assertThat(customers.get(2l), is(equalTo(pieDoe)));

    expectedException.expect(DataAccessException.class);
    expectedException.expectCause(is(nullValue(Throwable.class)));
    expectedException.expectMessage(is(equalTo("expected 1 Customer with ID (4); but found (2) matches")));

    customerDao.findById(4l);
  }

  @Test
  public void findByIdThrowsQueryException() {
    expectedException.expect(DataAccessException.class);
    expectedException.expectCause(CoreMatchers.isA(QueryInvalidException.class));
    expectedException.expectMessage(is(equalTo(String.format("failed to find Customer by ID (%1$d)", Long.MAX_VALUE))));

    customerDao.findById(Long.MAX_VALUE);
  }

}
