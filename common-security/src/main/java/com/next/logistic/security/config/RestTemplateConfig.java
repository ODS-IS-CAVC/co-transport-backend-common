package com.next.logistic.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.time.Duration;

@Configuration
@Slf4j
public class RestTemplateConfig {

    private static final int CONNECT_TIMEOUT = 90000; // Connection timeout 90s
    private static final int READ_TIMEOUT = 90000; // Read timeout 90s

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(this::clientHttpRequestFactory) // Custom request factory
                .setConnectTimeout(Duration.ofMillis(CONNECT_TIMEOUT)) // Set connection timeout
                .setReadTimeout(Duration.ofMillis(READ_TIMEOUT)) // Set read timeout
                .uriTemplateHandler(uriBuilderFactory()) // Custom URI template handler
                .errorHandler(new CustomResponseErrorHandler()) // Error handling
                .interceptors(loggingInterceptor()) // Add logging for requests/responses
                .build();
    }

    /**
     * Custom request factory with timeout configurations.
     */
    private ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        factory.setReadTimeout(READ_TIMEOUT);
        return factory;
    }

    /**
     * Custom URI Builder Factory to handle special characters safely in URLs.
     */
    private DefaultUriBuilderFactory uriBuilderFactory() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY); // Encode query params only
        return factory;
    }

    /**
     * Custom error handler for detailed error handling.
     */
    public static class CustomResponseErrorHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getStatusCode().isError();
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            String body = new String(response.getBody().readAllBytes());
            log.error("Error Response: {} - Body: {}", response.getStatusCode(), body);
            throw new HttpClientErrorException(response.getStatusCode(), body);
        }
    }

    /**
     * Logging interceptor for debugging requests and responses.
     */
    private ClientHttpRequestInterceptor loggingInterceptor() {
        return (request, body, execution) -> {
            log.info("Request URI: {}", request.getURI());
            log.info("Request Method: {}", request.getMethod());
            log.info("Request Headers: {}", request.getHeaders());
            if (body.length > 0) {
                log.info("Request Body: {}", new String(body));
            }
            ClientHttpResponse response = execution.execute(request, body);
            log.info("Response Status Code: {}", response.getStatusCode());
            return response;
        };
    }
}
