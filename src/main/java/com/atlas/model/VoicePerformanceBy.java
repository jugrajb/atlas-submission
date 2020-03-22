package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoicePerformanceBy {
    private int cid;
    private int pid;

    public VoicePerformanceBy(@JsonProperty("cid") int cid,
                              @JsonProperty("pid") int pid) {
        this.cid = cid;
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public int getPid() {
        return pid;
    }
}
