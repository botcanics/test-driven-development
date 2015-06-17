package org.tdd.example.app.extensions.gemfire.cache;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.DataPolicy;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.tdd.example.core.lang.Assert;
import org.tdd.example.core.lang.ObjectUtils;

/**
 * The RegionFactoryBean class...
 *
 * @author John Blum
 * @see org.springframework.beans.factory.FactoryBean
 * @see org.springframework.beans.factory.InitializingBean
 * @see com.gemstone.gemfire.cache.Cache
 * @see com.gemstone.gemfire.cache.Region
 * @see com.gemstone.gemfire.cache.RegionFactory
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class RegionFactoryBean<K, V> implements FactoryBean<Region<K, V>>, InitializingBean {

  protected static final int DEFAULT_INITIAL_CAPACITY = 16;

  protected static final float DEFAULT_LOAD_FACTOR = 0.75f;

  private Cache gemfireCache;

  private Class<K> keyConstraint;
  private Class<V> valueConstraint;

  private DataPolicy dataPolicy;

  private Float loadFactor;

  private Integer initialCapacity;

  private Region<K, V> region;

  private String name;

  @Override
  public void afterPropertiesSet() throws Exception {
    Cache localCache = getCache();

    RegionFactory<K, V> regionFactory = localCache.createRegionFactory();

    regionFactory.setDataPolicy(getDataPolicy());
    regionFactory.setInitialCapacity(getInitialCapacity());
    regionFactory.setLoadFactor(getLoadFactor());
    regionFactory.setKeyConstraint(getKeyConstraint());
    regionFactory.setValueConstraint(getValueConstraint());

    region = regionFactory.create(getName());
  }

  @Override
  public Region<K, V> getObject() throws Exception {
    Assert.state(region != null, "The Region was not properly initialized!");
    return region;
  }

  @Override
  public Class<?> getObjectType() {
    return (region != null ? region.getClass() : Region.class);
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  public final void setCache(final Cache gemfireCache) {
    this.gemfireCache = gemfireCache;
  }

  protected Cache getCache() {
    Assert.state(gemfireCache != null, "The GemFire Cache reference was not properly initialized");
    return gemfireCache;
  }

  public final void setName(final String name) {
    this.name = name;
  }

  protected String getName() {
    Assert.state(!(name == null || name.trim().isEmpty()), "Region 'name' was not specified");
    return name;
  }

  public final void setDataPolicy(final DataPolicy dataPolicy) {
    this.dataPolicy = dataPolicy;
  }

  protected DataPolicy getDataPolicy() {
    return ObjectUtils.defaultIfNull(dataPolicy, DataPolicy.DEFAULT);
  }

  public final void setInitialCapacity(final Integer initialCapacity) {
    this.initialCapacity = initialCapacity;
  }

  protected Integer getInitialCapacity() {
    return ObjectUtils.defaultIfNull(initialCapacity, DEFAULT_INITIAL_CAPACITY);
  }

  public final void setLoadFactor(final Float loadFactor) {
    this.loadFactor = loadFactor;
  }

  protected Float getLoadFactor() {
    return ObjectUtils.defaultIfNull(loadFactor, DEFAULT_LOAD_FACTOR);
  }

  public final void setKeyConstraint(final Class<K> keyConstraint) {
    this.keyConstraint = keyConstraint;
  }

  protected Class<K> getKeyConstraint() {
    return keyConstraint;
  }

  public final void setValueConstraint(final Class<V> valueConstraint) {
    this.valueConstraint = valueConstraint;
  }

  protected Class<V> getValueConstraint() {
    return valueConstraint;
  }

}
