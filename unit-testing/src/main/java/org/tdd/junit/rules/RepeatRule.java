package org.tdd.junit.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.tdd.junit.Repeat;

/**
 * The RepeatRule class is a JUnit TestRule that enables an appropriately @Repeat annotated test case method
 * to be repeated a specified number of times.
 *
 * @author John Blum
 * @see org.junit.rules.TestRule
 * @see org.junit.runner.Description
 * @see org.junit.runners.model.Statement
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class RepeatRule implements TestRule {

  protected static final int DEFAULT_REPETITIONS = 1;

  @Override
  public Statement apply(final Statement statement, final Description description) {
    return new Statement() {
      @Override public void evaluate() throws Throwable {
        RepeatRule.this.evaluate(statement, description);
      }
    };
  }

  protected void evaluate(final Statement statement, final Description description) throws Throwable {
    if (isTest(description)) {
      Repeat repeat = description.getAnnotation(Repeat.class);

      for (int count = 0, repetitions = getRepetitions(repeat); count < repetitions; count++) {
        statement.evaluate();
      }
    }
  }

  private int getRepetitions(final Repeat repeat) {
    int repetitions = DEFAULT_REPETITIONS;

    if (repeat != null) {
      try {
        repetitions = Integer.parseInt(repeat.value());
      }
      catch (NumberFormatException ignore) {
        repetitions = Integer.getInteger(repeat.value(), DEFAULT_REPETITIONS);
      }
    }

    return repetitions;
  }

  private boolean isTest(final Description description) {
    return (description.isSuite() || description.isTest());
  }

}
