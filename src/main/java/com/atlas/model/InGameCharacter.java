package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InGameCharacter {
    private final int cid;
    private final String name;

    public InGameCharacter(int cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public InGameCharacter(@JsonProperty("name") String name) {
        this.cid = 0;
        this.name = name;
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }
}
