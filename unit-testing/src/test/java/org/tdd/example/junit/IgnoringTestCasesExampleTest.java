package org.tdd.example.junit;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.tdd.junit.ConditionalIgnore;
import org.tdd.junit.rules.ConditionalIgnoreRule;

/**
 * The IgnoringTestCasesExampleTest class...
 *
 * @author John Blum
 * @see org.junit.Ignore
 * @see org.junit.Test
 * @since 1.0.0
 */
public class IgnoringTestCasesExampleTest {

  @Rule
  public ConditionalIgnoreRule ignoreRule = new ConditionalIgnoreRule();

  @Test
  public void enabledTestCase() {
    System.out.println("Enabled Test");
  }

  @Test
  @Ignore("Disabled test due to unreliable code and constant failures!")
  public void disabledTest() {
    fail("Code Smell!!!");
  }

  @Test
  @ConditionalIgnore(value = "Conditionally disabled test due to unreliable code and constant failures!", until = "2016-01-14")
  public void conditionallyDisabledTest() {
    fail("Smelly Code!!!");
  }

}
