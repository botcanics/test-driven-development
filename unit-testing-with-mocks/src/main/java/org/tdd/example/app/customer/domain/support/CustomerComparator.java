package org.tdd.example.app.customer.domain.support;

import java.util.Comparator;

import org.tdd.example.app.customer.domain.Customer;
import org.tdd.example.core.util.ComparatorUtils;

/**
 * The CustomerComparator class...
 *
 * @author John Blum
 * @see org.tdd.example.app.customer.domain.Customer
 * @see org.tdd.example.core.util.ComparatorUtils
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class CustomerComparator implements Comparator<Customer> {

  @Override
  public int compare(final Customer customer1, final Customer customer2) {
    int result;

    return ((result = ComparatorUtils.compare(customer1.getLastName(), customer2.getLastName())) != 0 ? result
      : ((result = ComparatorUtils.compare(customer1.getFirstName(), customer2.getFirstName())) != 0 ? result
      : ComparatorUtils.compare(customer1.getId(), customer2.getId())));
  }

}
