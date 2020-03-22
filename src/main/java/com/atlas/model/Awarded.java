package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Awarded {
    private String name;
    private int oid;
    private int gid;
    private int year;

    public Awarded(@JsonProperty("name") String name,
                   @JsonProperty("oid") int oid,
                   @JsonProperty("gid") int gid,
                   @JsonProperty("year") int year) {
        this.name = name;
        this.oid = oid;
        this.gid = gid;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getOid() {
        return oid;
    }

    public int getGid() {
        return gid;
    }

    public int getYear() {
        return year;
    }
}
