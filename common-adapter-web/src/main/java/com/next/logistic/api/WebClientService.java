package com.next.logistic.api;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.*;

@Service
public class WebClientService {

    // baseUrlごとにWebClientを保持するプール（マルチスレッド対応のためにConcurrentHashMapを使用）
    private final Map<String, WebClient> webClientPool = new ConcurrentHashMap<>();

    // requestIdごとにレスポンスを保存するストア
    private final Map<String, CompletableFuture<Object>> responseStore = new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * プールからWebClientを取得するか、存在しない場合は新しく作成します。
     *
     * @param baseUrl ベースURL
     * @return WebClientインスタンス
     */
    private WebClient getOrCreateWebClient(String baseUrl) {
        return webClientPool.computeIfAbsent(baseUrl, url ->
                WebClient.builder()
                        .baseUrl(url)
                        .defaultHeader("Content-Type", "application/json")
                        .build()
        );
    }

    /**
     * リクエストにヘッダーを追加します。
     *
     * @param request リクエストヘッダー仕様
     * @param headers ヘッダーのマップ
     * @return ヘッダーが適用されたリクエスト
     */
    private WebClient.RequestHeadersSpec<?> applyHeaders(WebClient.RequestHeadersSpec<?> request, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            request.headers(httpHeaders -> headers.forEach(httpHeaders::set));
        }
        return request;
    }

    /**
     * リクエストのレスポンスをresponseStoreに保存します。
     *
     * @param requestId リクエストID
     * @param responseMono レスポンスのMono
     * @param <T> レスポンスの型
     */
    private <T> void storeResponse(String requestId, Mono<T> responseMono) {
        CompletableFuture<Object> future = new CompletableFuture<>();
        responseMono.subscribe(future::complete, future::completeExceptionally);
        responseStore.put(requestId, future);

        // 3分後にレスポンスを削除するスケジュールを設定
        scheduler.schedule(() -> {
            responseStore.remove(requestId);
            System.out.println("Removed response for requestId: " + requestId);
        }, 40, TimeUnit.SECONDS);
    }

    /**
     * GETリクエストを送信します（非同期または同期をサポート）。
     *
     * @param requestId リクエストID
     * @param baseUrl ベースURL
     * @param uri リクエストURI
     * @param headers ヘッダーのマップ
     * @param responseType レスポンスの型
     * @param async 非同期フラグ
     * @param <T> レスポンスの型パラメータ
     * @return レスポンスデータまたはnull（非同期の場合）
     */
    public <T> T get(String requestId, String baseUrl, String uri, Map<String, String> headers, Class<T> responseType, boolean async) {
        WebClient webClient = getOrCreateWebClient(baseUrl);
        Mono<T> responseMono = applyHeaders(webClient.get().uri(uri), headers)
                .retrieve()
                .bodyToMono(responseType);

        if (async) {
            storeResponse(requestId, responseMono);
            return null;
        } else {
            return responseMono.block();
        }
    }

    /**
     * POSTリクエストを送信します（非同期または同期をサポート）。
     *
     * @param requestId リクエストID
     * @param baseUrl ベースURL
     * @param uri リクエストURI
     * @param headers ヘッダーのマップ
     * @param requestBody リクエストボディ
     * @param responseType レスポンスの型
     * @param async 非同期フラグ
     * @param <T> リクエストボディの型パラメータ
     * @param <R> レスポンスの型パラメータ
     * @return レスポンスデータまたはnull（非同期の場合）
     */
    public <T, R> R post(String requestId, String baseUrl, String uri, Map<String, String> headers, T requestBody, Class<R> responseType, boolean async) {
        WebClient webClient = getOrCreateWebClient(baseUrl);
        Mono<R> responseMono = applyHeaders(webClient.post().uri(uri).bodyValue(requestBody), headers)
                .retrieve()
                .bodyToMono(responseType);

        if (async) {
            storeResponse(requestId, responseMono);
            return null;
        } else {
            return responseMono.block();
        }
    }

    /**
     * PUTリクエストを送信します（非同期または同期をサポート）。
     *
     * @param requestId リクエストID
     * @param baseUrl ベースURL
     * @param uri リクエストURI
     * @param headers ヘッダーのマップ
     * @param requestBody リクエストボディ
     * @param responseType レスポンスの型
     * @param async 非同期フラグ
     * @param <T> リクエストボディの型パラメータ
     * @param <R> レスポンスの型パラメータ
     * @return レスポンスデータまたはnull（非同期の場合）
     */
    public <T, R> R put(String requestId, String baseUrl, String uri, Map<String, String> headers, T requestBody, Class<R> responseType, boolean async) {
        WebClient webClient = getOrCreateWebClient(baseUrl);
        Mono<R> responseMono = applyHeaders(webClient.put().uri(uri).bodyValue(requestBody), headers)
                .retrieve()
                .bodyToMono(responseType);

        if (async) {
            storeResponse(requestId, responseMono);
            return null;
        } else {
            return responseMono.block();
        }
    }

    /**
     * DELETEリクエストを送信します（非同期または同期をサポート）。
     *
     * @param requestId リクエストID
     * @param baseUrl ベースURL
     * @param uri リクエストURI
     * @param headers ヘッダーのマップ
     * @param responseType レスポンスの型
     * @param async 非同期フラグ
     * @param <T> レスポンスの型パラメータ
     * @return レスポンスデータまたはnull（非同期の場合）
     */
    public <T> Object delete(String requestId, String baseUrl, String uri, Map<String, String> headers, Class<T> responseType, boolean async) {
        WebClient webClient = getOrCreateWebClient(baseUrl);
        Mono<T> responseMono = applyHeaders(webClient.delete().uri(uri), headers)
                .retrieve()
                .bodyToMono(responseType);

        if (async) {
            storeResponse(requestId, responseMono);
            return null;
        } else {
            return responseMono.block();
        }
    }

    /**
     * requestIdに基づいてリクエストの結果を取得します。
     *
     * @param requestId リクエストID
     * @param responseType レスポンスの型
     * @param <T> レスポンスの型パラメータ
     * @return レスポンスデータ
     */
    public <T> T getResponse(String requestId, Class<T> responseType) {
        CompletableFuture<Object> future = responseStore.get(requestId);
        if (future != null) {
            try {
                return responseType.cast(future.get(5, TimeUnit.SECONDS)); // 最大5秒待機
            } catch (Exception e) {
                throw new RuntimeException("Error getting response for requestId: " + requestId, e);
            }
        }
        return null;
    }

    /**
     * アプリケーションが停止する際にスケジューラをシャットダウンしてメモリリークを防ぎます。
     */
    @PreDestroy
    public void shutdownScheduler() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
        System.out.println("Scheduler shut down successfully.");
    }

    /**
     * プールに存在するbaseUrlのリストを取得します。
     *
     * @return baseUrlとWebClientのマップ
     */
    public Map<String, WebClient> getWebClientPool() {
        return webClientPool;
    }

    public Map<String, CompletableFuture<Object>> getResponseStore() {
        return responseStore;
    }
}
