package org.tdd.example.math;

/**
 * Algorithm is an abstract based class containing operations common to all Algorithm implementations.
 *
 * @author John Blum
 * @see org.tdd.example.math.FactorialAlgorithm
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class Algorithm {

  /**
   * Asserts that the Object reference is not null.
   *
   * @param obj the Object reference used in the assertion.
   * @param message a String containing the message used in the Exception thrown from this assertion.
   * @throws java.lang.IllegalArgumentException if the Object reference is null.
   * @see #assertTrue(boolean, String)
   */
  protected static void assertNotNull(Object obj, String message) {
    assertTrue(obj != null, message);
  }

  /**
   * Asserts the condition is true.
   *
   * @param condition the boolean value used in the assertion.
   * @param message a String containing the message used in the Exception thrown from this assertion.
   * @throws java.lang.IllegalArgumentException if the boolean condition is false.
   */
  protected static void assertTrue(boolean condition, String message) {
    if (!condition) {
      throw new IllegalArgumentException(message);
    }
  }

}
