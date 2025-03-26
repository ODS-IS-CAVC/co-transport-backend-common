package com.next.logistic.authorization;

import com.next.logistic.annotation.AllowQuery;
import com.next.logistic.exception.NextMapperException;

import java.lang.reflect.Field;

public interface AuthorizationFieldReader {

    <T> Object read(T entity) throws IllegalAccessException;

    class QueryFieldReader implements AuthorizationFieldReader {

        private final Field field;

        public QueryFieldReader(Field field) {
            this.field = field;
        }

        @Override
        public <T> Object read(T object) throws NextMapperException, IllegalAccessException {
            final AllowQuery column = field.getAnnotation(AllowQuery.class);
            if (column == null) {
                throw new NextMapperException("Not have permission for this field, field " + field.getName());
            }
            field.setAccessible(true);
            return field.get(object);
        }
    }

    class NoopFieldReader implements AuthorizationFieldReader {
        private final Field field;

        public NoopFieldReader(Field field) {
            this.field = field;
        }

        @Override
        public <T> Object read(T entity) throws NextMapperException {
            throw new NextMapperException("Not have permission for this field, field " + field.getName());
        }
    }

    static AuthorizationFieldReader of(Field field) {
        if (field.getAnnotation(AllowQuery.class) != null) {
            return new QueryFieldReader(field);
        } else {
            return new NoopFieldReader(field);
        }
    }
}
