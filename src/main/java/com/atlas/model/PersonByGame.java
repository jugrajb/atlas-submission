package com.atlas.model;

public class PersonByGame {
    private int pid;
    private String firstname;
    private String lastname;
    private String role;

    public PersonByGame(int pid, String firstname, String lastname, String role) {
        this.pid = pid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
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

    public String getRole() {
        return role;
    }
}
