package org.tdd.example.math;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.tdd.example.math.support.BigFactorialAlgorithm;
import org.tdd.example.math.support.IterativeFactorialAlgorithm;
import org.tdd.example.math.support.RecursiveFactorialAlgorithm;
import org.junit.Test;

/**
 * The FactorialAlgorithmsPerformanceTest class...
 *
 * @author John Blum
 * @see org.tdd.example.math.FactorialAlgorithm
 * @see org.tdd.example.math.support.BigFactorialAlgorithm
 * @see org.tdd.example.math.support.IterativeFactorialAlgorithm
 * @see org.tdd.example.math.support.RecursiveFactorialAlgorithm
 * @since 1.0.0
 */
public class FactorialAlgorithmsPerformanceTest {

  private static final int ITERATIONS = 1000;

  private BigFactorialAlgorithm bigFactorial = new BigFactorialAlgorithm();
  private IterativeFactorialAlgorithm iterativeFactorial = new IterativeFactorialAlgorithm();
  private RecursiveFactorialAlgorithm recursiveFactorial = new RecursiveFactorialAlgorithm();

  protected long getFactorialAlgorithmRuntime(int iterations, FactorialAlgorithm factorialAlgorithm) {
    final long t0 = System.currentTimeMillis();

    for (int number = iterations; number >= 0; number--) {
      factorialAlgorithm.factorial(number);
    }

    return (System.currentTimeMillis() - t0);
  }

  @Test
  public void progressivePerformanceGains() {
    final long recursiveFactorialRuntime = getFactorialAlgorithmRuntime(ITERATIONS, new FactorialAlgorithm() {
      @Override public int factorial(final int number) {
        return recursiveFactorial.factorial(number);
      }
    });

    final long iterativeFactorialRuntime = getFactorialAlgorithmRuntime(ITERATIONS, new FactorialAlgorithm() {
      @Override public int factorial(final int number) {
        return iterativeFactorial.factorial(number);
      }
    });

    final long bigFactorialRuntime = getFactorialAlgorithmRuntime(ITERATIONS, new FactorialAlgorithm() {
      @Override public int factorial(final int number) {
        return bigFactorial.factorial(BigInteger.valueOf(number)).intValue();
      }
    });

    System.out.printf("Recursive factorial algorithm runtime is %1$d ms%n", recursiveFactorialRuntime);
    System.out.printf("Iterative factorial algorithm runtime is %1$d ms%n", iterativeFactorialRuntime);
    System.out.printf("Big factorial algorithm runtime is %1$d ms%n", bigFactorialRuntime);

    assertTrue(String.format(
      "Expected 'Iterative' factorial (%1$d) to be faster than 'Recursive' factorial (%2$d)!",
        iterativeFactorialRuntime, recursiveFactorialRuntime), iterativeFactorialRuntime <= recursiveFactorialRuntime);

    assertTrue(String.format(
      "Expected 'Big' factorial (%1$d) to be faster than 'Recursive' factorial (%2$d",
        bigFactorialRuntime, recursiveFactorialRuntime), bigFactorialRuntime <= recursiveFactorialRuntime);
  }

}
