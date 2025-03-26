package com.next.logistic.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NextBeanUtils {

    public static <T> String[] getNullPropertyNames(T source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static <T> void copyProperties(T src, T target, String... ignoreProperties) {
        BeanUtils.copyProperties(src, target, ignoreProperties);
    }

    public static <T> void copyPropertiesIgnoreNullField(T src, T target,
                                                         String... ignoreProperties) {

        Set<String> emptyNames = new HashSet<>(Arrays.asList(ignoreProperties));
        emptyNames.addAll(List.of(getNullPropertyNames(src)));
        String[] result = new String[emptyNames.size()];

        BeanUtils.copyProperties(src, target, emptyNames.toArray(result));
    }
}
