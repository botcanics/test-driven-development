package org.tdd.junit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Repeat class is a Java Annotation enabling an annotated test suite class test case method to be repeated
 * a specified number of iterations.
 *
 * @author John Blum
 * @see java.lang.annotation.Annotation
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@SuppressWarnings("unused")
public @interface Repeat {

  String value() default "1";

}
