package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameStudio {
    private int sid;
    private String name;
    private String city;
    private String state;

    public GameStudio(int sid, String name, String city, String state) {
        this.sid = sid;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public GameStudio(@JsonProperty("name") String name,
                      @JsonProperty("city") String city,
                      @JsonProperty("state") String state) {
        this.sid = 0;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
