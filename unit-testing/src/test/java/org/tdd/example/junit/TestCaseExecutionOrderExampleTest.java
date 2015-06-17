package org.tdd.example.junit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * The TestCaseExecutionOrderExampleTest class...
 *
 * @author John Blum
 * @see org.junit.Test
 * @see org.junit.runners.MethodSorters
 * @since 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCaseExecutionOrderExampleTest {

  @Test
  public void testCase4() {
    System.out.println(4);
  }

  @Test
  public void testCase2() {
    System.out.println(2);
  }

  @Test
  public void testCase3() {
    System.out.println(3);
  }

  @Test
  public void testCase1() {
    System.out.println(1);
  }

}
