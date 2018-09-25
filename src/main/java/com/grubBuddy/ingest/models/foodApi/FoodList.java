package com.grubBuddy.ingest.models.foodApi;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lt",
        "start",
        "end",
        "total",
        "sr",
        "sort",
        "item"
})
public class FoodList implements Serializable
{

    @JsonProperty("lt")
    public String lt;
    @JsonProperty("start")
    public Integer start;
    @JsonProperty("end")
    public Integer end;
    @JsonProperty("total")
    public Integer total;
    @JsonProperty("sr")
    public String sr;
    @JsonProperty("sort")
    public String sort;
    @JsonProperty("item")
    public java.util.List<FoodItem> foodItems = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7808295883457841640L;

    public FoodList withLt(String lt) {
        this.lt = lt;
        return this;
    }

    public FoodList withStart(Integer start) {
        this.start = start;
        return this;
    }

    public FoodList withEnd(Integer end) {
        this.end = end;
        return this;
    }

    public FoodList withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public FoodList withSr(String sr) {
        this.sr = sr;
        return this;
    }

    public FoodList withSort(String sort) {
        this.sort = sort;
        return this;
    }

    public FoodList withItem(List<FoodItem> item) {
        this.foodItems = item;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public FoodList withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}