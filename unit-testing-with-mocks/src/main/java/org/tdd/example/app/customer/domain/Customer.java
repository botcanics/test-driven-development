package org.tdd.example.app.customer.domain;

import org.tdd.example.app.customer.domain.support.CustomerComparator;
import org.tdd.example.core.lang.ObjectUtils;

/**
 * The Customer class...
 *
 * @author John Blum
 * @see java.lang.Comparable
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class Customer implements Comparable<Customer> {

  private Long id;

  private String firstName;
  private String lastName;

  public Customer() {
  }

  public Customer(final Long id) {
    this.id = id;
  }

  public Customer(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getName() {
    return String.format("%1$s %2$s", getFirstName(), getLastName());
  }

  @Override
  public int compareTo(final Customer customer) {
    return new CustomerComparator().compare(this, customer);
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Customer)) {
      return false;
    }

    Customer that = (Customer) obj;

    return ObjectUtils.equalsIgnoreNull(this.getId(), that.getId())
      && ObjectUtils.nullSafeEquals(this.getFirstName(), that.getFirstName())
      && ObjectUtils.nullSafeEquals(this.getLastName(), that.getLastName());
  }

  @Override
  public int hashCode() {
    int hashValue = 17;
    hashValue = 37 * hashValue + ObjectUtils.nullSafeHashCode(getId());
    hashValue = 37 * hashValue + ObjectUtils.nullSafeHashCode(getFirstName());
    hashValue = 37 * hashValue + ObjectUtils.nullSafeHashCode(getLastName());
    return hashValue;
  }

  @Override
  public String toString() {
    return getName();
  }

}
