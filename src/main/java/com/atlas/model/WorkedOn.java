package com.atlas.model;

public class WorkedOn {
    private int gid;
    private int pid;
    private String role;

    public WorkedOn(int gid, int pid, String role) {
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
