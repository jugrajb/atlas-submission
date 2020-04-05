package com.atlas.model;

public class GamePhotos {
    private final int gphid;
    private final String cover;
    private final String banner;

    public GamePhotos(int gphid, String cover, String banner) {
        this.gphid = gphid;
        this.cover = cover;
        this.banner = banner;
    }

    public int getGphid() {
        return gphid;
    }

    public String getCover() {
        return cover;
    }

    public String getBanner() {
        return banner;
    }
}
