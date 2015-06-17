package org.tdd.example.core.lang;

/**
 * The Assert class...
 *
 * @author John Blum
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class Assert {

  public static void isTrue(boolean condition, String message) {
    isTrue(condition, new IllegalArgumentException(message));
  }

  public static void isTrue(boolean condition, RuntimeException cause) {
    if (!condition) {
      throw cause;
    }
  }
  public static void notNull(Object obj, final String message) {
    if (obj == null) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void state(boolean state, String message) {
    if (!state) {
      throw new IllegalStateException(message);
    }
  }

}
