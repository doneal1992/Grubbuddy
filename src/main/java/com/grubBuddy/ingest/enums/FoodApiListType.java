package com.grubBuddy.ingest.enums;

import com.grubBuddy.ingest.constants.MongoConstants;

public enum FoodApiListType {
    Food("f", MongoConstants.FoodComposition.FOODS),
    Nutrient("n", MongoConstants.FoodComposition.NUTRIENTS);

    private String type;
    private String mongoCollection;

    public String getType() {
        return this.type;
    }

    public String getMongoCollection() {
        return this.mongoCollection;
    }

    FoodApiListType(String type, String mongoCollection) {
        this.type = type;
        this.mongoCollection = mongoCollection;
    }

}
