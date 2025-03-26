package com.next.logistic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The NextPersonalizationFilterMapping annotation is an attribute of <em> NextDataAuthorization
 * </em>, to map field of data entity with specific role and values in dynamic.<br/>
 *
 * <b>field</b>: is property name in JPA entity that is the target to filter in entity object.<br/>
 *
 * <b>role</b>: a Data_realm_roles in keycloak. to detect what user need to trigger the
 * filtering.<br/>
 * <b>values</b>: List of values that the field's values is matched <br/>
 *
 *
 * <b><em>Example:</em></b><br/>
 * "@NextPersonalizationFilterMapping(field = "status",role = "KT",values =
 * {"WAITING_KT_APPROVE"})"<br/> Entity PurchaseOrderEntity <br/>
 * <b>field</b>: status (attribute name of PurchaseOrderEntity) correspond to the mapped column in
 * the database table in JPA<br/>
 * <b>role</b>: "KT", Any user is assigned this roles in keycloak, this mapping will be
 * affected.<br/>
 * <b>values</b>: {'WAITING_KT_APPROVE'}<br/>
 * <em>For this instance</em>: The system automatically filters <b><em>Purchase Order </em> </b> has
 * status ="WAITING_KT_APPROVE" for all user has role: "KTT"
 *
 */

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface NextPersonalizationFilterMapping {

    String field() default "";//status property in db entity that is used to filter

    String role() default "";//realm roles that is assigned.

    String[] values() default {};//list of status of data, that matches to each role

}
