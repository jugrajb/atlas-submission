package com.atlas.model;

public class GameCard {
    private int gid;
    private String title;

    public GameCard(int gid, String title) {
        this.gid = gid;
        this.title = title;
    }

    public int getGid() {
        return gid;
    }

    public String getTitle() {
        return title;
    }
}
