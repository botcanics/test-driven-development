package org.tdd.example.junit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.Thread.State;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.tdd.example.math.FactorialAlgorithm;
import org.tdd.example.math.support.BigFactorialAlgorithm;

/**
 * The AssertionExamplesTest class...
 *
 * @author John Blum
 * @see org.junit.Assert
 * @see org.junit.Test
 * @since 1.0.0
 */
public class AssertionExamplesTest {

  private FactorialAlgorithm factorialAlgorithm = new BigFactorialAlgorithm();

  private List<Integer> fibonacciSequence = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89);

  @Test
  public void factorialOfValidNumbers() {
    assertThat(factorialAlgorithm.factorial(1), is(equalTo(1)));
    assertThat(factorialAlgorithm.factorial(2), is(equalTo(2)));
    assertThat(factorialAlgorithm.factorial(3), is(equalTo(6)));
    assertThat(factorialAlgorithm.factorial(4), is(equalTo(24)));
    assertThat(factorialAlgorithm.factorial(5), is(equalTo(120)));
    assertThat(factorialAlgorithm.factorial(6), is(equalTo(720)));
  }

  @Test
  public void assertThatIsTypeable() {
    String threadState = "RUNNABLE";

    assertEquals(State.RUNNABLE, threadState);

    //assertThat(threadState, is(State.RUNNABLE));
  }

  @Test
  public void fibonacciValuesContain() {
    assertThat(8, org.hamcrest.Matchers.isIn(fibonacciSequence));
    assertThat(10, not(org.hamcrest.Matchers.isIn(fibonacciSequence)));
  }

  @Test
  public void otherAssertThatMatcherCombinations() {
    assertThat(Arrays.asList("test", "testing", "tested"), everyItem(containsString("test")));
    assertThat("abc", allOf(containsString("a"), containsString("c")));
    assertThat("abc", anyOf(containsString("d"), containsString("e"), containsString("b")));
    assertThat("abc", both(containsString("a")).and(containsString("c")));
    assertThat("abc", either(containsString("d")).or(containsString("b")));
  }

}
