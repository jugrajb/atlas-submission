package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class VideoGamePerson {
    private final int pid;
    private final String firstname;
    private final String lastname;
    private final Date birthdate;
    private final int sid;

    public VideoGamePerson( int pid,
                            String firstname,
                            String lastname,
                            Date birthdate,
                            int sid
    ) {
        this.pid = pid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.sid = sid;
    }

    public VideoGamePerson(@JsonProperty("firstname") String firstname,
                           @JsonProperty("lastname") String lastname,
                           @JsonProperty("birthdate") String birthdate,
                           @JsonProperty("sid") int sid
    ) {
        this.pid = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = Date.valueOf(birthdate);
        this.sid = sid;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public int getSid() {
        return sid;
    }
}
