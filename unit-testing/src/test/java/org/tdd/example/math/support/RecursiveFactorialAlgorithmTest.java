package org.tdd.example.math.support;

import static org.junit.Assert.*;

import org.tdd.example.math.FactorialAlgorithm;
import org.junit.Test;

/**
 * The RecursiveFactorialAlgorithmTest class is a test suite of test cases testing the contract and functionality
 * of the recursive factorial algorithm.
 *
 * @author John Blum
 * @see org.tdd.example.math.FactorialAlgorithm
 * @see org.tdd.example.math.support.RecursiveFactorialAlgorithm
 * @since 1.0.0
 */
public class RecursiveFactorialAlgorithmTest {

  protected FactorialAlgorithm factorialAlgorithm = newFactorialAlgorithm();

  protected FactorialAlgorithm newFactorialAlgorithm() {
    return new RecursiveFactorialAlgorithm();
  }

  @Test
  public void factorialOfValidNumbers() {
    assertEquals(1, factorialAlgorithm.factorial(1));
    assertEquals(2, factorialAlgorithm.factorial(2));
    assertEquals(6, factorialAlgorithm.factorial(3));
    assertEquals(24, factorialAlgorithm.factorial(4));
    assertEquals(120, factorialAlgorithm.factorial(5));
    assertEquals(720, factorialAlgorithm.factorial(6));
    assertEquals(5040, factorialAlgorithm.factorial(7));
    assertEquals(40320, factorialAlgorithm.factorial(8));
    assertEquals(362880, factorialAlgorithm.factorial(9));
    assertEquals(3628800, factorialAlgorithm.factorial(10));
    assertEquals(39916800, factorialAlgorithm.factorial(11));
    assertEquals(479001600, factorialAlgorithm.factorial(12));
  }

  @Test
  public void factorialOfZero() {
    assertEquals(1, factorialAlgorithm.factorial(0));
  }

  // What happens if I do not include this test case in my test suite?
  @Test(expected = IllegalArgumentException.class)
  public void factorialOfNegativeNumber() {
    factorialAlgorithm.factorial(-4);
  }

  //@Test
  //public void factorialHandlesStackOverflow() {
  //  factorialAlgorithm.factorial(20000);
  //}

}
