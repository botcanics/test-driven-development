package org.tdd.example.app.customer.dao;

/**
 * The DataAccessException class...
 *
 * @author John Blum
 * @see java.lang.RuntimeException
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class DataAccessException extends RuntimeException {

  public DataAccessException() {
  }

  public DataAccessException(final String message) {
    super(message);
  }

  public DataAccessException(final Throwable cause) {
    super(cause);
  }

  public DataAccessException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
