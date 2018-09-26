package com.grubBuddy.ingest.dao;

import com.grubBuddy.ingest.models.foodApi.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Repository
public class FoodDAO {
    @Autowired
    ReactiveMongoTemplate reactiveFoodTemplate;

    public Flux<FoodItem> insertMany(Mono<List<FoodItem>> foodItems, String collection) {
        return this.reactiveFoodTemplate.insertAll(foodItems,collection);
    }
}
