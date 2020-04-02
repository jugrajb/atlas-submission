package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Auth {
  private int uid;
  private String email;
  private String password;
  private String username;

  public Auth(@JsonProperty("email") String email,
              @JsonProperty("password") String password) {
    this.email = email;
    this.password = password;
    this.username = "";
    this.uid = 0;
  }

  public Auth(int uid, String email, String password, String username) {
    this.uid = uid;
    this.email = email;
    this.password = password;
    this.username = username;
  }

  public int getUid() {
    return uid;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }
}
