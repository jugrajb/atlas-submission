package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishingCompany {
    private int pcid;
    private String name;
    private String city;
    private String state;

    public PublishingCompany(int pcid, String name, String city, String state) {
        this.pcid = pcid;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public PublishingCompany(@JsonProperty("name") String name,
                             @JsonProperty("city") String city,
                             @JsonProperty("state") String state) {
        this.pcid = 0;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public int getPcid() {
        return pcid;
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
