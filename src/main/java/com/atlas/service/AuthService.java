package com.atlas.service;

import com.atlas.dao.AuthDAO;
import com.atlas.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private AuthDAO authDAO;

  @Autowired
  public AuthService(@Qualifier("postgres-auth") AuthDAO authDAO) {
    this.authDAO = authDAO;
  }

  public int login(Auth auth) {
    return authDAO.login(auth);
  }

  public int signUp(Auth auth, String username) {
    return authDAO.signUp(auth, username);
  }
}
