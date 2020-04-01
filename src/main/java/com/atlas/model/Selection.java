package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Selection {
    private List<Condition> conditions;
    private String require;    // "ANY" or "ALL"
    private List<String> sort;
    private List<String> directions;  // "ASC" or "DESC"

    public Selection(@JsonProperty("conditions") List<Condition> conditions,
                     @JsonProperty("require") String require,
                     @JsonProperty("sort") List<String> sort,
                     @JsonProperty("directions") List<String> directions) {
        this.conditions = conditions;
        this.require = require;
        this.sort = sort;
        this.directions = directions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public String getRequire() {
        return require;
    }

    public List<String> getSort() {
        return sort;
    }

    public List<String> getDirections() {
        return directions;
    }
}
