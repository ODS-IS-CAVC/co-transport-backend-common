package com.next.logistic.convert;

import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class StringArrayToStringConverter extends GenericListToStringConverter<String> {
    @Override
    protected String convertElementToString(String element) {
        return element.toString();
    }

    @Override
    protected String convertStringToElement(String value) {
        return value;
    }
}
