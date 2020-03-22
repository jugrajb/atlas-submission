package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoGameAward {
    private String name;
    private int oid;

    public VideoGameAward(@JsonProperty("name") String name,
                          @JsonProperty("oid") int oid) {
        this.name = name;
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public int getOid() {
        return oid;
    }
}
