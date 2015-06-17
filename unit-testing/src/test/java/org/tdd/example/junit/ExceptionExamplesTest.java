package org.tdd.example.junit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.tdd.example.math.FactorialAlgorithm;
import org.tdd.example.math.support.BigFactorialAlgorithm;

/**
 * The ExceptionExamplesTest class...
 *
 * @author John Blum
 * @see org.junit.Rule
 * @see org.junit.Test
 * @see org.junit.rules.ExpectedException
 * @since 1.0.0
 */
public class ExceptionExamplesTest {

  private FactorialAlgorithm factorialAlgorithm = new BigFactorialAlgorithm();

  // NOTE requires @Rule annotation and public instance variable
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @SuppressWarnings("unused")
  public void testFactorialOfNegativeNumberThrowsException() {
    try {
      factorialAlgorithm.factorial(-4);
      fail("Cannot compute the factorial of a negative number (-4)!");
    }
    catch (IllegalArgumentException ignore) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void factorialOfNegativeNumber() {
    factorialAlgorithm.factorial(-4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void factorialOfNegativeNumberThrowsException() {
    try {
      factorialAlgorithm.factorial(-4);
    }
    catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage(), is(equalTo(
        String.format(FactorialAlgorithm.NUMBER_LESS_THAN_ZERO_ERROR_MESSAGE, -4))));
      assertThat(expected.getCause(), is(nullValue()));
      throw expected;
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void factorialOfNullValue() {
    try {
      ((BigFactorialAlgorithm) factorialAlgorithm).factorial(null);
    }
    catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage(), is(equalTo("value must not be null")));
      assertThat(expected.getCause(), is(nullValue()));
      throw expected;
    }
  }

  @Test
  public void expectExceptionOnFactorialOfNegativeNumber() {
    expectedException.expectMessage(String.format(FactorialAlgorithm.NUMBER_LESS_THAN_ZERO_ERROR_MESSAGE, -4));
    expectedException.expectCause(is(nullValue(Throwable.class)));
    factorialAlgorithm.factorial(-4);
  }

}
