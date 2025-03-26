package com.next.logistic.annotation;

import com.next.logistic.enums.ValueType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The XFilterMapping annotation is an attribute of <em> XDataAuthorization </em>, to map
 * field of entity to type of filters to build the dynamic filter.<br/>
 *
 * <b>field</b>: is property name in JPA entity that is the target to filter in entity object.<br/>
 *
 * <b>permission</b>: list of data_roles in keycloak. to detect what user need to trigger the
 * filtering.<br/>
 *
 * <b>values</b>: Value of field<br/>
 *
 * <b>type</b>: #ValueType.normal: get origin value from values object, #ValueType.transform:
 * transform field from #User object in #UserContext<br/>
 * <p>
 * Syntax example: abc(0).node(10).(#10).(0) mean get element 0 of list abc, after that get value of
 * field name node, next get element index 10 of list node, next get key 10 of map, finally get
 * index 0 of list
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface MatrixPermission {

    String field(); //property in db entity that is mapped to column in database , used for building rsql query.


    String[] permissions() default {}; //list of client roles and realm role for each function

    String[] values();

    ValueType type() default ValueType.normal;

    String operation() default "=in=";

}
