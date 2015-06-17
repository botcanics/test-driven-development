package org.tdd.example.core.lang;

/**
 * The ObjectUtils class...
 *
 * @author John Blum
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class ObjectUtils {

  public static <T> T defaultIfNull(T value, T defaultValue) {
    return (value != null ? value : defaultValue);
  }

  public static boolean equalsIgnoreNull(Object obj1, Object obj2) {
    return (obj1 == null ? obj2 == null : obj1.equals(obj2));
  }

  public static boolean nullSafeEquals(Object obj1, Object obj2) {
    return (obj1 != null && obj1.equals(obj2));
  }

  public static int nullSafeHashCode(Object obj) {
    return (obj != null ? obj.hashCode() : 0);
  }

  public static String nullSafeToString(Object obj) {
    return String.valueOf(obj);
  }

}
