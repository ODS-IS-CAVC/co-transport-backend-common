package com.next.logistic.elastic.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ElasticService {

    private final ElasticsearchClient client;
    private final int maxAttempts;
    private final long backoffDelay;

    public ElasticService(ElasticsearchClient client, int maxAttempts, long backoffDelay) {
        this.client = client;
        this.maxAttempts = maxAttempts;
        this.backoffDelay = backoffDelay;
    }

    public <T> List<Hit<T>> searchWithRetry(SearchRequest searchRequest, Class<T> clazz) {
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                attempt++;
                log.info("Attempt {} to execute search query...", attempt);
                SearchResponse<T> response = client.search(searchRequest, clazz);
                log.info("Search query succeeded.");
                return response.hits().hits();
            } catch (Exception e) {
                log.error("Error executing search query on attempt {}: {}", attempt, e.getMessage());
                if (attempt >= maxAttempts) {
                    throw new RuntimeException("Max retry attempts reached. Unable to execute search query.", e);
                }
                try {
                    Thread.sleep(backoffDelay);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return List.of();
    }
}
