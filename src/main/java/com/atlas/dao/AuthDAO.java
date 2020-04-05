package com.atlas.dao;

import com.atlas.model.Auth;

import java.util.Map;

public interface AuthDAO {
  Map<String, Object> login(Auth auth);

  int signUp(Auth auth, String username);
}
