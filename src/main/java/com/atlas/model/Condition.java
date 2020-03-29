package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Condition {
    private String attribute;
    private String comparator;
    private Object value;

    public Condition(@JsonProperty("attribute") String attribute,
                     @JsonProperty("comparator") String comparator,
                     @JsonProperty("value") Object value) {
        this.attribute = attribute;
        this.comparator = comparator;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getComparator() {
        return comparator;
    }

    public Object getValue() {
        return value;
    }
}
