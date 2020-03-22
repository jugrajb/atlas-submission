package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayableOn {
    private int gid;
    private int gpid;

    public PlayableOn(@JsonProperty("gid") int gid,
                      @JsonProperty("gpid") int gpid) {
        this.gid = gid;
        this.gpid = gpid;
    }

    public int getGid() {
        return gid;
    }

    public int getGpid() {
        return gpid;
    }
}
