package com.next.logistic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface MatrixUpdate {

    /**
     * Ignore update field with {@link #permissions()}
     */
    String[] ignoreProperties();

    /**
     *
     * Additional Fields option
     */
    String[] additionalFields() default {} ;

    String[] permissions() default {}; //list of client roles and realm role for each function

    /**
     * Index of param object extends BaseDataUpdateAction class
     */
    int indexOfParam();



}
