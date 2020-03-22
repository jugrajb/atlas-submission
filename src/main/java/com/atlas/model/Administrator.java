package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Administrator {
    private final int uid;
    private final String firstname;
    private final String lastname;

    public Administrator(
            @JsonProperty("uid") int uid,
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname
    ) {
        this.uid = uid;
        this.firstname  = firstname;
        this.lastname = lastname;
    }

    public Administrator(
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname
    ) {
        this.uid = 0;
        this.firstname  = firstname;
        this.lastname = lastname;
    }

    public int getUid() {
        return uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
