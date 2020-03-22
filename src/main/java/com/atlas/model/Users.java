package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {
    private final int uid;
    private final String email;
    private final String password;

    public Users(
            @JsonProperty("uid") int uid,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password
    ) {
        this.uid = uid;
        this.email  = email;
        this.password = password;
    }

    public Users(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password
    ) {
        this.uid = 0;
        this.email  = email;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
