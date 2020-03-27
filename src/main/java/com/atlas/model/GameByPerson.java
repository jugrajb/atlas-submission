package com.atlas.model;

import java.sql.Date;

public class GameByPerson {
    private int gid;
    private String title;
    private Date releaseDate;

    public GameByPerson(int gid, String title, Date releaseDate) {
        this.gid = gid;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public int getGid() {
        return gid;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
