package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class PasswordChange {
    private int id;
    private int uid;
    private String oldPassword;
    private String newPassword;
    private Timestamp changedOn;

    public PasswordChange(@JsonProperty("id") int id,
                          @JsonProperty("uid") int uid,
                          @JsonProperty("oldPassword") String oldPassword,
                          @JsonProperty("newPassword") String newPassword,
                          @JsonProperty("changedOn") Timestamp changedOn
    ) {
        this.id = id;
        this.uid = uid;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.changedOn = changedOn;
    }

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public Timestamp getChangedOn() {
        return changedOn;
    }
}
