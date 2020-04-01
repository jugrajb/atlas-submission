package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Director {
    private final int pid;
    private final String specialization;

    public Director(@JsonProperty("pid") int pid, @JsonProperty("specialization") String specialization) {
        this.pid = pid;
        this.specialization = specialization;
    }

//    public Director(@JsonProperty("specialization") String specialization) {
//        this.pid = 0;
//        this.specialization = specialization;
//    }

    public int getPid() {
        return pid;
    }

    public String getSpecialization() {
        return specialization;
    }
}
