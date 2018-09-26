package com.grubBuddy.ingest.models.foodApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.grubBuddy.ingest.api.FoodItemDeserializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "offset",
        "id",
        "name"
})

@JsonDeserialize(using = FoodItemDeserializer.class)
public interface FoodItem
{
}