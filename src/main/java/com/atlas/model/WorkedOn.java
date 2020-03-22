package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkedOn {
    private int gid;
    private int pid;
    private String role;

    public WorkedOn(@JsonProperty("gid") int gid,
                    @JsonProperty("pid") int pid,
                    @JsonProperty("role") String role) {
        this.gid = gid;
        this.pid = pid;
        this.role = role;
    }

    public int getGid() {
        return gid;
    }

    public int getPid() {
        return pid;
    }

    public String getRole() {
        return role;
    }
}
