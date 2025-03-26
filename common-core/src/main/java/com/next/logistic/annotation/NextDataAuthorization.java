package com.next.logistic.annotation;

import java.lang.annotation.*;

/**
 * {@code The <em> NextDataAuthorization </em> annotation serves for combination of data authorization in
 * Next Logistic, it includes attributes such as: XFilterMapping,   <br/> This
 * annotation will be used for all method that needs to filter data for one or many type of
 * dimensions such as: PSCL, PS, Department, Company, Personalization.<br/> Any method uses this
 * annotation, the system will be automatically filter data according to the instruction.<br/>
 * <b><em>Filtering Principles</em></b>:<br/>
 *
 * @XDataAuthorization(combineFilters = {
 *             @XFilterMapping(type = ValueType.transform, permissions = {"ADMIN_ORGANIZATION"},
 *                     field = "normal.user.code", values = {"strings(0)(0).xyz"}),
 *             @XFilterMapping(type = ValueType.transform, permissions = {"ADMIN_ORGANIZATION"},
 *                     field = "normal.user.code.name", values = {"groups"}),
 *
 *     }, skip = {"SYSTEM_ADMIN"})
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface NextDataAuthorization {
}
