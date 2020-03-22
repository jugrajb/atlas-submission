package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Series {
    private final int gid;
    private final int prevGid;

    public Series(
            @JsonProperty("gid") int gid,
            @JsonProperty("prevGid") int prevGid
    ) {
        this.gid = gid;
        this.prevGid  = prevGid;
    }

    public int getGid() {
        return gid;
    }

    public int getPrevGid() {
        return prevGid;
    }
}
