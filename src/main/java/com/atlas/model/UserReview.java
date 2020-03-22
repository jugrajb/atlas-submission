package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class UserReview {
    private final int rid;
    private final int uid;
    private final int gid;
    private final String title;
    private final String review;
    private final int score;
    private final Date date;

    public UserReview (
            @JsonProperty("rid") int rid,
            @JsonProperty("uid") int uid,
            @JsonProperty("gid") int gid,
            @JsonProperty("title") String title,
            @JsonProperty("review") String review,
            @JsonProperty("score") int score,
            @JsonProperty("date") Date date
    ) {
        this.rid = rid;
        this.uid = uid;
        this.gid = gid;
        this.title = title;
        this.review = review;
        this.score = score;
        this.date = date;
    }

    public UserReview (
            @JsonProperty("uid") int uid,
            @JsonProperty("gid") int gid,
            @JsonProperty("title") String title,
            @JsonProperty("review") String review,
            @JsonProperty("score") int score,
            @JsonProperty("date") Date date
    ) {
        this.rid = 0;
        this.uid = uid;
        this.gid = gid;
        this.title = title;
        this.review = review;
        this.score = score;
        this.date = date;
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

    public int getUid() {
        return uid;
    }

    public Date getDate() {
        return date;
    }
}
