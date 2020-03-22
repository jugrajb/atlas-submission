package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class GamePlatform {
    private int gpid;
    @NotBlank
    private String name;

    public GamePlatform(int gpid, String name) {
        this.gpid = gpid;
        this.name = name;
    }

    public GamePlatform(@JsonProperty("name") String name) {
        this.gpid = 0;
        this.name = name;
    }

    public int getGpid() {
        return gpid;
    }

    public String getName() {
        return name;
    }
}
