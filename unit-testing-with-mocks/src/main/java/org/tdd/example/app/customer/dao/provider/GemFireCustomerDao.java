package org.tdd.example.app.customer.dao.provider;

import java.util.List;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.query.SelectResults;

import org.tdd.example.app.customer.dao.CustomerDao;
import org.tdd.example.app.customer.dao.DataAccessException;
import org.tdd.example.app.customer.domain.Customer;
import org.tdd.example.core.lang.Assert;
import org.tdd.example.core.util.CollectionUtils;

/**
 * The GemFireCustomerDao class...
 *
 * @author John Blum
 * @see org.tdd.example.app.customer.dao.CustomerDao
 * @see org.tdd.example.app.customer.domain.Customer
 * @see com.gemstone.gemfire.cache.Region
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class GemFireCustomerDao implements CustomerDao {

  private Region<Long, Customer> customers;

  public GemFireCustomerDao(Region<Long, Customer> customers) {
    Assert.notNull(customers, "'Customers' Region must not be null");
    this.customers = customers;
  }

  protected Region<Long, Customer> getCustomersRegion() {
    Assert.state(customers != null, "'Customers' Region was not properly initialized!");
    return customers;
  }

  protected List<Customer> runQuery(String query) throws Exception {
    SelectResults<Customer> selectResults = getCustomersRegion().query(query);

    return CollectionUtils.nullSafeList(selectResults != null ? selectResults.asList() : null);
  }

  @Override
  public Customer findById(Long id) {
    try {
      List<Customer> customers = runQuery(String.format("id = %1$s", id));

      if (customers.size() != 1) {
        throw new DataAccessException(String.format("expected 1 Customer with ID (%1$d); but found (%2$d) matches",
          id, CollectionUtils.size(customers)));
      }

      return customers.get(0);
    }
    catch (Exception e) {
      throw (e instanceof DataAccessException ? (DataAccessException) e :
        new DataAccessException(String.format("failed to find Customer by ID (%1$d)", id), e));
    }
  }

  @Override
  public List<Customer> findByLastName(String lastName) {
    try {
      return runQuery(String.format("lastName = %1$s", lastName));
    }
    catch (Exception e) {
      throw (e instanceof DataAccessException ? (DataAccessException) e
        : new DataAccessException(String.format("failed to find all Customers by last name (%1$s)", lastName)));
    }
  }

}
