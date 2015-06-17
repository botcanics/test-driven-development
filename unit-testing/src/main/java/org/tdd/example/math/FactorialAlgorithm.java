package org.tdd.example.math;

/**
 * FactorialAlgorithm is an abstract base class for all Factorial Algorithm implementations.
 *
 * @author John Blum
 * @see org.tdd.example.math.Algorithm
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class FactorialAlgorithm extends Algorithm {

  public static final String NUMBER_LESS_THAN_ZERO_ERROR_MESSAGE = "value (%1$d) must be greater than equal to 0";

  /**
   * Computes the factorial of the given number.
   *
   * @param value an Integer value used to compute the factorial.
   * @return the factorial of the given number.
   */
  public abstract int factorial(int value);

  /**
   * Returns the String representation of the factorial algorithm.
   *
   * @return a String describing the factorial algorithm.
   */
  @Override
  public String toString() {
    return "n! = factorial(n)";
  }

}
