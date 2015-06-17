package org.tdd.junit.support;

import org.junit.runner.Description;
import org.tdd.junit.IgnoreCondition;

/**
 * The DefaultIgnoreCondition class...
 *
 * @author John Blum
 * @see org.junit.runner.Description
 * @see org.tdd.junit.ConditionalIgnore
 * @see org.tdd.junit.IgnoreCondition
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class DefaultIgnoreCondition implements IgnoreCondition {

  public static final boolean DEFAULT_IGNORE = false;

  public static final DefaultIgnoreCondition DO_NOT_IGNORE = new DefaultIgnoreCondition(false);
  public static final DefaultIgnoreCondition IGNORE = new DefaultIgnoreCondition(true);

  private final boolean ignore;

  public DefaultIgnoreCondition() {
    this(DEFAULT_IGNORE);
  }

  public DefaultIgnoreCondition(final boolean ignore) {
    this.ignore = ignore;
  }

  public boolean isIgnore() {
    return ignore;
  }

  @Override
  public boolean evaluate(final Description testCaseDescription) {
    return isIgnore();
  }

}
