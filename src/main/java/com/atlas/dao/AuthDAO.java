package com.atlas.dao;

import com.atlas.model.Auth;

public interface AuthDAO {
  int login(Auth auth);

  int signUp(Auth auth, String username);
}
