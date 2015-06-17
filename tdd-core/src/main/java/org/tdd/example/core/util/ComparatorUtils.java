package org.tdd.example.core.util;

/**
 * The ComparatorUtils class...
 *
 * @author John Blum
 * @see java.lang.Comparable
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class ComparatorUtils {

  public static <T extends Comparable<T>> int compare(T obj1, T obj2) {
    return (obj1 == null ? 1 : (obj2 == null ? -1 : obj1.compareTo(obj2)));
  }

}
