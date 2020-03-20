package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoGame {
  private final int gid;
  private final String title;
  private final String releaseDate;
  private final String genre;
  private final int pcid;
  private final int sid;
  private final int pid;

  public VideoGame( int gid,
                    String title,
                    String releaseDate,
                    String genre,
                    int pcid,
                    int sid,
                    int pid
  ) {
    this.gid = gid;
    this.title = title;
    this.releaseDate = releaseDate;
    this.genre = genre;
    this.pcid = pcid;
    this.sid = sid;
    this.pid = pid;
  }

  public VideoGame(@JsonProperty("title") String title,
                   @JsonProperty("releaseDate") String releaseDate,
                   @JsonProperty("genre") String genre,
                   @JsonProperty("pcid") int pcid,
                   @JsonProperty("sid") int sid,
                   @JsonProperty("pid") int pid
  ) {
    this.gid = 0;
    this.title = title;
    this.releaseDate = releaseDate;
    this.genre = genre;
    this.pcid = pcid;
    this.sid = sid;
    this.pid = pid;
  }

  public String getTitle() {
    return title;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public String getGenre() {
    return genre;
  }

  public int getPcid() {
    return pcid;
  }

  public int getSid() {
    return sid;
  }

  public int getGid() {
    return gid;
  }

  public int getPid() {
    return pid;
  }
}
