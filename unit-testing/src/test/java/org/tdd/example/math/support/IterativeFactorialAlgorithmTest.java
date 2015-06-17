package org.tdd.example.math.support;

import org.tdd.example.math.FactorialAlgorithm;
import org.junit.Test;

/**
 * The IterativeFactorialAlgorithmTest class is a test suite of test cases testing the contract and functionality
 * of the iterative factorial algorithm.
 *
 * @author John Blum
 * @see org.tdd.example.math.FactorialAlgorithm
 * @see org.tdd.example.math.support.IterativeFactorialAlgorithm
 * @see org.tdd.example.math.support.RecursiveFactorialAlgorithmTest
 * @since 1.0.0
 */
public class IterativeFactorialAlgorithmTest extends RecursiveFactorialAlgorithmTest {

  @Override
  protected FactorialAlgorithm newFactorialAlgorithm() {
    return new IterativeFactorialAlgorithm();
  }

  @Test
  public void factorialHandlesStackOverflow() {
    factorialAlgorithm.factorial(20000);
  }

  //@Test
  //public void factorialHandlesNumericOverflow() {
  //  Assert.assertEquals(2432902008176640000l, factorialAlgorithm.factorial(20));
  //  Assert.assertEquals(6227020800l, factorialAlgorithm.factorial(13));
  //}

}
