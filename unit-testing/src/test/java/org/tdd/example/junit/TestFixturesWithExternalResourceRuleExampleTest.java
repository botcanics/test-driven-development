package org.tdd.example.junit;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

/**
 * The TestFixturesWithExternalResourceRuleExampleTest class...
 *
 * @author John Blum
 * @see org.junit.ClassRule
 * @see org.junit.Rule
 * @see org.junit.Test
 * @see org.junit.rules.ExternalResource
 * @since 1.0.0
 */
public class TestFixturesWithExternalResourceRuleExampleTest {

  @ClassRule
  public static ExternalResource globalExternalResource = new ExternalResource() {
    @Override protected void before() throws Throwable {
      System.out.println("Before Test Suite");
    }

    @Override protected void after() {
      System.out.println("After Test Suite");
    }
  };

  @Rule
  public ExternalResource externalResource = new ExternalResource() {
    @Override protected void before() throws Throwable {
      System.out.println("Before Test Case");
    }

    @Override protected void after() {
      System.out.println("After Test Case");
    }
  };

  @Test
  public void one() {
    System.out.println("Test Case 1");
  }

  @Test
  public void two() {
    System.out.println("Test Case 2");
  }

  @Test
  public void three() {
    System.out.println("Test Case 3");
  }

}
