package com.next.logistic.convert;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.util.Strings;

@Slf4j
@Converter
public abstract class GenericListToStringConverter<T> implements AttributeConverter<List<T>, String> {

    @Override
    public String convertToDatabaseColumn(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.stream()
                .map(this::convertElementToString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<T> convertToEntityAttribute(String value) {
        if (Strings.isEmpty(value)) {
            return Collections.emptyList();
        }
        return Stream.of(value.split(", "))
                .map(this::convertStringToElement)
                .collect(Collectors.toList());
    }

    /**
     * Convert an element of type T to a String representation.
     * @param element Element to convert
     * @return String representation of the element
     */
    protected abstract String convertElementToString(T element);

    /**
     * Convert a String representation to an element of type T.
     * @param value String representation
     * @return Element of type T
     */
    protected abstract T convertStringToElement(String value);
}
