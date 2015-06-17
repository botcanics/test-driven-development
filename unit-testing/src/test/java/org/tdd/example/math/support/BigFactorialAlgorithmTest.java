package org.tdd.example.math.support;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.tdd.example.math.FactorialAlgorithm;
import org.junit.Test;

/**
 * The BigFactorialAlgorithmTest class is a test suite of test cases testing the contract and functionality
 * of the iterative factorial algorithm using the BigInteger type to store the results of the factorial calculation.
 *
 * @author John Blum
 * @see org.tdd.example.math.FactorialAlgorithm
 * @see org.tdd.example.math.support.BigFactorialAlgorithm
 * @see org.tdd.example.math.support.IterativeFactorialAlgorithmTest
 * @since 1.0.0
 */
public class BigFactorialAlgorithmTest extends IterativeFactorialAlgorithmTest {

  protected BigFactorialAlgorithm bigFactorialAlgorithm = (BigFactorialAlgorithm) factorialAlgorithm;

  @Override
  protected FactorialAlgorithm newFactorialAlgorithm() {
    return new BigFactorialAlgorithm();
  }

  @Test
  @SuppressWarnings("deprecation")
  public void factorialHandlesNumericOverflow() {
    assertEquals(2432902008176640000l, bigFactorialAlgorithm.factorial(BigInteger.valueOf(20)).longValue());
    assertEquals(6227020800l, bigFactorialAlgorithm.factorial(BigInteger.valueOf(13)).longValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void factorialOfNullValue() {
    try {
      bigFactorialAlgorithm.factorial(null);
    }
    catch (IllegalArgumentException expected) {
      assertEquals("value must not be null", expected.getMessage());
      assertNull(expected.getCause());
      throw expected;
    }
  }

}
