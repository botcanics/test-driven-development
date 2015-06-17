package org.tdd.example.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The CollectionUtils class...
 *
 * @author John Blum
 * @see java.util.Collection
 * @see java.util.Collections
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class CollectionUtils {

  public static <T> List<T> asList(T... array) {
    List<T> arrayList = new ArrayList<T>(array.length);
    Collections.addAll(arrayList, array);
    return arrayList;
  }

  public static boolean isEmpty(Collection collection) {
    return (collection == null || collection.isEmpty());
  }

  public static boolean isNotEmpty(Collection collection) {
    return !isEmpty(collection);
  }

  public static <T> List<T> nullSafeList(List<T> list) {
    return (list != null ? list : Collections.<T>emptyList());
  }

  public static int size(Collection collection) {
    return (isEmpty(collection) ? 0 : collection.size());
  }

}
