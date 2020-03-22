package com.atlas.model;

public class PlayableOn {
    private int gid;
    private int gpid;

    public PlayableOn(int gid, int gpid) {
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
