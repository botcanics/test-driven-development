package org.tdd.example.math.support;

import org.tdd.example.math.FactorialAlgorithm;

/**
 * RecursiveFactorialAlgorithm is an implementation of the factorial mathematical calculation
 * using a recursive algorithm.
 *
 * How many test cases should the 'factorial' method have?
 *
 * How many bugs are in this factorial algorithm?
 *
 * @author John Blum
 * @see org.tdd.example.math.FactorialAlgorithm
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class RecursiveFactorialAlgorithm extends FactorialAlgorithm {

  /**
   * Computes the factorial of the given number using a recursive algorithm.
   *
   * @param value an Integer value used to compute the factorial.
   * @return the factorial of the given number.
   * @throws java.lang.IllegalArgumentException if the number value is less than 0.
   */
  @Override
  public int factorial(int value) {
    assertTrue(value >= 0, String.format(NUMBER_LESS_THAN_ZERO_ERROR_MESSAGE, value));

    if (value == 0) {
      return 1;
    }

    return (value * factorial(value - 1));
  }

}
