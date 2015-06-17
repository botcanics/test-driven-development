package org.tdd.example.junit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.tdd.example.math.FactorialAlgorithm;
import org.tdd.example.math.support.BigFactorialAlgorithm;

/**
 * The ParameterizedExampleTest class...
 *
 * @author John Blum
 * @see org.junit.Test
 * @see org.junit.runners.Parameterized
 * @see org.junit.runners.Parameterized.Parameters
 * @since 1.0.0
 */
@RunWith(Parameterized.class)
public class ParameterizedExampleTest {

  // Imagine data loaded from external data source using a Iterable "facade"
  @Parameters(name = "{index}: factorial({0}) = {1}")
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][] {
      { 0, 1 }, { 1, 1 }, { 2, 2 }, { 3, 6 }, { 4, 24 }, { 5, 120 }, { 6, 720 }
    });
  }

  private FactorialAlgorithm factorialAlgorithm = new BigFactorialAlgorithm();

  private Integer actualInput;
  private Integer expectedOutput;

  public ParameterizedExampleTest(final Integer input, final Integer output) {
    this.actualInput = input;
    this.expectedOutput = output;
  }

  @Test
  public void factorialOfValidNumbers() {
    assertThat(factorialAlgorithm.factorial(actualInput), is(equalTo(expectedOutput)));
  }

}
