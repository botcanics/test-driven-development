package org.tdd.junit;

import org.junit.runner.Description;

/**
 * The IgnoreCondition class...
 *
 * @author John Blum
 * @see org.junit.runner.Description
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface IgnoreCondition {

  boolean evaluate(Description testCaseDescription);

}
