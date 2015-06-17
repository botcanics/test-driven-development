package org.tdd.example.app.extensions.gemfire.cache;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.DataPolicy;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionAttributes;
import com.gemstone.gemfire.cache.RegionFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.tdd.example.app.customer.domain.Customer;

/**
 * The RegionFactoryBeanTest class...
 *
 * @author John Blum
 * @see org.junit.Test
 * @see org.mockito.Mockito
 * @see org.tdd.example.app.extensions.gemfire.cache.RegionFactoryBean
 * @see com.gemstone.gemfire.cache.Cache
 * @see com.gemstone.gemfire.cache.Region
 * @see com.gemstone.gemfire.cache.RegionAttributes
 * @see com.gemstone.gemfire.cache.RegionFactory
 * @since 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class RegionFactoryBeanTest {

  @Mock
  private Cache mockGemFireCache;

  @Mock
  private Region mockRegion;

  @Mock
  private RegionAttributes mockRegionAttributes;

  @Mock
  private RegionFactory mockRegionFactory;

  @Before
  public void setup() {
    when(mockGemFireCache.createRegionFactory()).thenReturn(mockRegionFactory);

    doAnswer(new Answer<Void>() {
      @Override public Void answer(final InvocationOnMock invocationOnMock) throws Throwable {
        DataPolicy dataPolicy = invocationOnMock.getArgumentAt(0, DataPolicy.class);
        when(mockRegionAttributes.getDataPolicy()).thenReturn(dataPolicy);
        return null;
      }
    }).when(mockRegionFactory).setDataPolicy(Matchers.any(DataPolicy.class));

    doAnswer((new Answer<Void>() {
      @Override public Void answer(final InvocationOnMock invocationOnMock) throws Throwable {
        Integer initialCapacity = invocationOnMock.getArgumentAt(0, Integer.class);
        when(mockRegionAttributes.getInitialCapacity()).thenReturn(initialCapacity);
        return null;
      }
    })).when(mockRegionFactory).setInitialCapacity(anyInt());

    doAnswer((new Answer<Void>() {
      @Override public Void answer(final InvocationOnMock invocationOnMock) throws Throwable {
        Float loadFactor = invocationOnMock.getArgumentAt(0, Float.class);
        when(mockRegionAttributes.getLoadFactor()).thenReturn(loadFactor);
        return null;
      }
    })).when(mockRegionFactory).setLoadFactor(anyInt());

    doAnswer((new Answer<Void>() {
      @Override public Void answer(final InvocationOnMock invocationOnMock) throws Throwable {
        Class<?> keyConstraint = invocationOnMock.getArgumentAt(0, Class.class);
        when(mockRegionAttributes.getKeyConstraint()).thenReturn(keyConstraint);
        return null;
      }
    })).when(mockRegionFactory).setKeyConstraint(Matchers.any(Class.class));

    doAnswer((new Answer<Void>() {
      @Override public Void answer(final InvocationOnMock invocationOnMock) throws Throwable {
        Class<?> valueConstraint = invocationOnMock.getArgumentAt(0, Class.class);
        when(mockRegionAttributes.getValueConstraint()).thenReturn(valueConstraint);
        return null;
      }
    })).when(mockRegionFactory).setValueConstraint(Matchers.any(Class.class));

    when(mockRegionFactory.create(anyString())).thenAnswer(new Answer<Region>() {
      @Override public Region answer(final InvocationOnMock invocationOnMock) throws Throwable {
        String name = invocationOnMock.getArgumentAt(0, String.class);

        when(mockRegion.getName()).thenReturn(name);
        when(mockRegion.getFullPath()).thenReturn((String.format("%1$s%2$s", Region.SEPARATOR, name)));
        when(mockRegion.getAttributes()).thenReturn(mockRegionAttributes);

        return mockRegion;
      }
    });
  }

  @Test
  public void regionInitialization() throws Exception {
    RegionFactoryBean<Long, Customer> factoryBean = new RegionFactoryBean<Long, Customer>();

    factoryBean.setCache(mockGemFireCache);
    factoryBean.setName("Customers");
    factoryBean.setDataPolicy(DataPolicy.PARTITION);
    factoryBean.setInitialCapacity(101);
    factoryBean.setLoadFactor(0.85f);
    factoryBean.setKeyConstraint(Long.class);
    factoryBean.setValueConstraint(Customer.class);
    factoryBean.afterPropertiesSet();

    Region<Long, Customer> customers = factoryBean.getObject();

    assertThat(customers, is(notNullValue()));
    assertThat(customers.getName(), is(equalTo("Customers")));
    assertThat(customers.getFullPath(), is(equalTo("/Customers")));
    assertThat(customers.getAttributes(), is(notNullValue()));
    assertThat(customers.getAttributes().getDataPolicy(), is(equalTo(DataPolicy.PARTITION)));
    assertThat(customers.getAttributes().getInitialCapacity(), is(equalTo(101)));
    assertThat(customers.getAttributes().getLoadFactor(), is(equalTo(0.85f)));
    assertThat(customers.getAttributes().getKeyConstraint(), is(equalTo(Long.class)));
    assertThat(customers.getAttributes().getValueConstraint(), is(equalTo(Customer.class)));

    verify(mockGemFireCache, times(1)).createRegionFactory();
    verify(mockRegionFactory, times(1)).setDataPolicy(eq(DataPolicy.PARTITION));
    verify(mockRegionFactory, times(1)).setInitialCapacity(eq(101));
    verify(mockRegionFactory, times(1)).setLoadFactor(eq(0.85f));
    verify(mockRegionFactory, times(1)).setKeyConstraint(eq(Long.class));
    verify(mockRegionFactory, times(1)).setValueConstraint(eq(Customer.class));
    verify(mockRegionFactory, times(1)).create(eq("Customers"));
  }

}
