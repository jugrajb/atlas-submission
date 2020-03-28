package com.atlas.model;

public class CharacterVa {
    private int gid;
    private int cid;
    private int pid;
    private String title;
    private String character;
    private String firstname;
    private String lastname;

    public CharacterVa(int gid, int cid, int pid, String title, String character, String firstname, String lastname) {
        this.gid = gid;
        this.cid = cid;
        this.pid = pid;
        this.title = title;
        this.character = character;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getGid() {
        return gid;
    }

    public int getCid() {
        return cid;
    }

    public int getPid() {
        return pid;
    }

    public String getTitle() {
        return title;
    }

    public String getCharacter() {
        return character;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
