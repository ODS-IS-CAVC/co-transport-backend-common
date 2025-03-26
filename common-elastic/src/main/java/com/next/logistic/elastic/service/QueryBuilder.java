package com.next.logistic.elastic.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;

public class QueryBuilder {

    public static SearchRequest matchQuery(String indexName, String field, String value) {
        Query query = Query.of(q -> q
                .match(m -> m
                        .field(field)
                        .query(value)
                )
        );

        return SearchRequest.of(r -> r
                .index(indexName)
                .query(query)
        );
    }
}
