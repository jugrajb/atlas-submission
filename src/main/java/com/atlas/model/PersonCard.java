package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonCard {
    private int pid;
    private String firstname;
    private String lastname;

    public PersonCard(@JsonProperty("pid") int pid,
                      @JsonProperty("firstname") String firstname,
                      @JsonProperty("lastname") String lastname) {
        this.pid = pid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getPid() {
        return pid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
