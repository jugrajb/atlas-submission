package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppearsIn {
    private int gid;
    private int cid;

    public AppearsIn(@JsonProperty("gid") int gid,
                     @JsonProperty("cid")int cid) {
        this.gid = gid;
        this.cid = cid;
    }

    public int getGid() {
        return gid;
    }

    public int getCid() {
        return cid;
    }
}
