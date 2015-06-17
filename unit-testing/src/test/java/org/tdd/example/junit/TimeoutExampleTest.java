package org.tdd.example.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * The TimeoutExampleTest class...
 *
 * @author John Blum
 * @see org.junit.Rule
 * @see org.junit.Test
 * @see org.junit.rules.Timeout
 * @since 1.0.0
 */
public class TimeoutExampleTest {

  protected static final long TIMEOUT = 1000;

  @Rule
  public Timeout timeout = Timeout.seconds(2);

  protected void function(final long pause) {
    final long timeout = (System.currentTimeMillis() + pause);

    while (System.currentTimeMillis() < timeout) {
      try {
        Thread.sleep(pause / 4);
      }
      catch (InterruptedException ignore) {
      }
    }
  }

  @Test(timeout = 500)
  public void functionWillTimeout() {
    function(TIMEOUT);
  }

  @Test
  public void functionWillNotTimeout() {
    function(TIMEOUT);
  }

}
