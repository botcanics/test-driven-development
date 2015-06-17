package org.tdd.example.math.support;

/**
 * IterativeFactorialAlgorithm is an implementation of the factorial mathematical calculation
 * using an iterative algorithm.
 *
 * How many bugs in this factorial algorithm?
 *
 * @author John Blum
 * @see org.tdd.example.math.support.RecursiveFactorialAlgorithm
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class IterativeFactorialAlgorithm extends RecursiveFactorialAlgorithm {

  /**
   * Computes the factorial of the given number using an iterative algorithm.
   *
   * @param value an Integer value used to compute the factorial.
   * @return the factorial of the given number.
   * @throws java.lang.IllegalArgumentException if the number value is less than 0.
   */
  @Override
  public int factorial(int value) {
    assertTrue(value >= 0, String.format(NUMBER_LESS_THAN_ZERO_ERROR_MESSAGE, value));

    int result = Math.max(1, value);

    for (value = result - 1; value > 0; value--) {
      result *= value;
    }

    return result;
  }

}
