package org.tdd.junit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.tdd.junit.support.DefaultIgnoreCondition;

/**
 * The ConditionalIgnore class is a Java Annotation used to annotated a test suite class test case method in order to
 * conditionally ignore the test case for a fixed amount of time, or based on a predetermined condition provided by
 * the IgnoreCondition interface.
 *
 * @author John Blum
 * @see java.lang.annotation.Annotation
 * @see org.tdd.junit.IgnoreCondition
 * @see org.tdd.junit.support.DefaultIgnoreCondition
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@SuppressWarnings("unused")
public @interface ConditionalIgnore {

  Class<? extends IgnoreCondition> condition() default DefaultIgnoreCondition.class;

  String until() default "1970-01-01";

  String value() default "";

}
