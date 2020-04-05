package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralUser {
    private final int uid;
    private final String username;
    private final String profileImage;

    public GeneralUser (
            int uid,
            String username,
            String profileImage //s3 link
    ) {
        this.uid = uid;
        this.username  = username;
        this.profileImage = profileImage;
    }

    public GeneralUser (
            @JsonProperty("username") String username
    ) {
        this.uid = 0;
        this.username  = username;
        this.profileImage = "";
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
