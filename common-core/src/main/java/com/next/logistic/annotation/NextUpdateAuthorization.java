package com.next.logistic.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface NextUpdateAuthorization {

    MatrixUpdate[] combineFilters() default {};

    /**
     * Default field ignore update
     */
    String[] defaultIgnoreProperties();

    /**
     * Index of param default extends #{BaseDataUpdateAction.class}
     */
    int defaultIndexParam() default 0;
}
