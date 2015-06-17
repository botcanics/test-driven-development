package org.tdd.example.math.support;

import java.math.BigInteger;

/**
 * BigFactorialAlgorithm class is an implementation of the factorial mathematical calculation
 * using an iterative algorithm on a number of type BigInteger to avoid numeric overflow.
 *
 * @author John Blum
 * @see java.math.BigInteger
 * @see org.tdd.example.math.support.IterativeFactorialAlgorithm
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class BigFactorialAlgorithm extends IterativeFactorialAlgorithm {

  protected static final BigInteger NEGATIVE_ONE = BigInteger.ONE.negate();
  protected static final BigInteger TWO = BigInteger.valueOf(2);

  /**
   * Computes the factorial of the given number using an iterative algorithm.
   *
   * WARNING this algorithm loses data for number values greater than 12.
   *
   * @param value an Integer value used to compute the factorial.
   * @return the factorial of the given number.
   * @throws java.lang.IllegalArgumentException if the number value is less than 0.
   * @see #factorial(java.math.BigInteger)
   * @deprecated please use factorial(:BigInteger):BigInteger for extremely large values of n.
   */
  @Override
  @Deprecated
  public int factorial(int value) {
    return factorial(BigInteger.valueOf(value)).intValue();
  }

  /**
   * Computes the factorial of the given number using an iterative algorithm and BigInteger value type
   * to avoid numeric overflow.
   *
   * @param value an Integer value used to compute the factorial.
   * @return the factorial of the given number.
   * @throws java.lang.IllegalArgumentException if the number value is null or less than 0.
   * @see #factorial(int)
   * @see java.math.BigInteger
   */
  public BigInteger factorial(BigInteger value) {
    assertNotNull(value, "value must not be null");

    assertTrue(value.compareTo(BigInteger.ZERO) >= 0, String.format(NUMBER_LESS_THAN_ZERO_ERROR_MESSAGE, value));

    if (value.compareTo(TWO) <= 0) {
      return (value.equals(TWO) ? TWO : BigInteger.ONE);
    }

    BigInteger result = value;

    for (value = result.add(NEGATIVE_ONE) ; value.compareTo(BigInteger.ONE) > 0; value = value.add(NEGATIVE_ONE)) {
      result = result.multiply(value);
    }

    return result;
  }

}
