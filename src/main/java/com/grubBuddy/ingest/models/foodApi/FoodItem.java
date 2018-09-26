package com.grubBuddy.ingest.models.foodApi;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "offset",
        "id",
        "name"
})
public class FoodItem implements Serializable
{

    @JsonProperty("offset")
    private Integer offset;

    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8169660668666539826L;

    public FoodItem withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public FoodItem withId(String id) {
        this.id = id;
        return this;
    }

    public FoodItem withName(String name) {
        this.name = name;
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

    public FoodItem withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}