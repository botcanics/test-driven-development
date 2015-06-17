package org.tdd.example.junit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assume.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The AssumptionExamplesTest class...
 *
 * @author John Blum
 * @see org.junit.Assume
 * @see org.junit.Test
 * @since 1.0.0
 */
public class AssumptionExamplesTest {

  protected static final String OPERATING_SYSTEM_NAME = System.getProperty("os.name", "unix").toLowerCase();

  @Before
  public void setup() {
    System.out.printf("Operation System (OS) name is (%1$s)%n", OPERATING_SYSTEM_NAME);
  }

  @Test
  public void onWindows() {
    assumeThat(OPERATING_SYSTEM_NAME, containsString("windows"));
    // add assertions appropriate for operating system here...
  }

  @Test
  public void onMacOSX() {
    assumeThat(OPERATING_SYSTEM_NAME, containsString("mac os x"));
    // add assertions appropriate for operating system here...
  }

  @Test
  public void onUnix() {
    assumeThat(OPERATING_SYSTEM_NAME, containsString("unix"));
    // add assertions appropriate for operating system here...
  }

}
