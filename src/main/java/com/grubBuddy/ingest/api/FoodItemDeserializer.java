package com.grubBuddy.ingest.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grubBuddy.ingest.models.foodApi.Food;
import com.grubBuddy.ingest.models.foodApi.FoodItem;
import com.grubBuddy.ingest.models.foodApi.FoodList;
import com.grubBuddy.ingest.models.foodApi.Nutrient;

import java.io.IOException;

public class FoodItemDeserializer extends JsonDeserializer<FoodItem> {

    @Override
    public FoodItem deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        JsonStreamContext parsingContext = jp.getParsingContext();
        JsonStreamContext parent = parsingContext.getParent();
        FoodList currentValue = (FoodList) parent.getCurrentValue();
        if (currentValue.lt.equals("f"))
            return mapper.readValue(root.toString(), Food.class);
        return mapper.readValue(root.toString(), Nutrient.class);
    }
}