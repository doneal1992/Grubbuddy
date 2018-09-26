package com.grubBuddy.ingest.enums;

public enum FoodApiListType {
    Food("f"),
    Nutrient("n");

    private String type;

    public String getType() {
        return this.type;
    }

    private FoodApiListType(String type) {
        this.type = type;
    }

}
