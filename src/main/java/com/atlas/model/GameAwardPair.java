package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameAwardPair {
    private int gid;
    private int oid;
    private String title;
    private String award;
    private String organization;
    private int year;

    public GameAwardPair(@JsonProperty("gid") int gid,
                         @JsonProperty("oid") int oid,
                         @JsonProperty("title") String title,
                         @JsonProperty("award") String award,
                         @JsonProperty("organization") String organization,
                         @JsonProperty("year") int year) {
        this.gid = gid;
        this.oid = oid;
        this.title = title;
        this.award = award;
        this.organization = organization;
        this.year = year;
    }

    public int getGid() {
        return gid;
    }

    public int getOid() {
        return oid;
    }

    public String getTitle() {
        return title;
    }

    public String getAward() {
        return award;
    }

    public String getOrganization() {
        return organization;
    }

    public int getYear() {
        return year;
    }
}
