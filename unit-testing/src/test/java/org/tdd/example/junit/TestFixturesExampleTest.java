package org.tdd.example.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The TestFixturesExampleTest class...
 *
 * @author John Blum
 * @see org.junit.After
 * @see org.junit.AfterClass
 * @see org.junit.Before
 * @see org.junit.BeforeClass
 * @see org.junit.Test
 * @since 1.0.0
 */
public class TestFixturesExampleTest {

  @BeforeClass
  public static void testSuiteSetup() {
    System.out.println("Before Test Suite");
  }

  @AfterClass
  public static void testSuiteTearDown() {
    System.out.println("After Test Suite");
  }

  @Before
  public void testCaseSetup() {
    System.out.println("Before Test Case");
  }

  @After
  public void testCaseTearDown() {
    System.out.println("After Test Case");
  }

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
