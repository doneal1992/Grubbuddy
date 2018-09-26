package com.grubBuddy.ingest.api;

import com.grubBuddy.ingest.models.foodApi.FoodApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FoodCompositionApi {

    @Value("${food-composition-base-url}")
    private String baseUrl;

    @Value("${food-composition-api-key}")
    private String apiKey;

    public Mono<FoodApiResponse> getFoodItems(int total, int page, String listType) {
        return WebClient
                .create(baseUrl)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/list")
                        .queryParam("format", "json")
                        .queryParam("lt", listType)
                        .queryParam("sort", "n")
                        .queryParam("max", total)
                        .queryParam("offset", page )
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve().bodyToMono(FoodApiResponse.class);

    }
}
