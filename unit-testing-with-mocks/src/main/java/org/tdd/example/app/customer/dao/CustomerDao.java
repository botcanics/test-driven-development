package org.tdd.example.app.customer.dao;

import java.util.List;

import org.tdd.example.app.customer.domain.Customer;

/**
 * The CustomerDao interface...
 *
 * @author John Blum
 * @see org.tdd.example.app.customer.domain.Customer
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface CustomerDao {

  Customer findById(Long id);

  List<Customer> findByLastName(String lastName);

}
