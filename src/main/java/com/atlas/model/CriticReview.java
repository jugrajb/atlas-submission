package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class CriticReview {
    private final int rid;
    private final int gid;
    private final String title;
    private final String review;
    private final int score;
    private final String author;
    private final String url;
    private final Date date;

    public CriticReview (
            int rid,
            int gid,
            String title,
            String review,
            int score,
            String author,
            String url,
            Date date
    ) {
        this.rid = rid;
        this.gid = gid;
        this.title = title;
        this.review = review;
        this.score = score;
        this.author = author;
        this.url = url;
        this.date = date;
    }

    public CriticReview (
            @JsonProperty("gid") int gid,
            @JsonProperty("title") String title,
            @JsonProperty("review") String review,
            @JsonProperty("score") int score,
            @JsonProperty("author") String author,
            @JsonProperty("url") String url,
            @JsonProperty("date") String date
    ) {
        this.rid = 0;
        this.gid = gid;
        this.title = title;
        this.review = review;
        this.score = score;
        this.author = author;
        this.url = url;
        this.date = Date.valueOf(date);
    }

    public int getRid() {
        return rid;
    }

    public int getGid() {
        return gid;
    }

    public String getTitle() {
        return title;
    }

    public String getReview() {
        return review;
    }

    public int getScore() {
        return score;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }
}
