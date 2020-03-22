package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.io.ByteArrayResource;

public class GeneralUser {
    private final int uid;
    private final String username;
    private final ByteArrayResource profileImage;

    public GeneralUser (
            @JsonProperty("uid") int uid,
            @JsonProperty("username") String username,
            @JsonProperty("profileImage") ByteArrayResource profileImage
    ) {
        this.uid = uid;
        this.username  = username;
        this.profileImage = profileImage;
    }

    public GeneralUser (
            @JsonProperty("username") String username,
            @JsonProperty("profileImage") ByteArrayResource profileImage
    ) {
        this.uid = 0;
        this.username  = username;
        this.profileImage = profileImage;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public ByteArrayResource getProfileImage() {
        return profileImage;
    }
}
