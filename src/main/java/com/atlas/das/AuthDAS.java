package com.atlas.das;

import com.atlas.dao.AuthDAO;
import com.atlas.model.Auth;
import com.atlas.model.GeneralUser;
import com.atlas.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postgres-auth")
public class AuthDAS implements AuthDAO {
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private static final int GENERAL_USER = 1;
  private static final int ADMIN_USER = 2;
  private static final int INVALID = 0;

  @Autowired
  public AuthDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public int login(Auth auth) {
    final String sql = "SELECT u.uid, g.username, u.email, u.password FROM users u " +
            "LEFT JOIN administrator a ON u.uid = a.uid " +
            "LEFT JOIN generaluser g ON u.uid = g.uid " +
            "WHERE u.email = :email AND u.password = :password";

    MapSqlParameterSource args = new MapSqlParameterSource();
    args.addValue("email", auth.getEmail());
    args.addValue("password", auth.getPassword());

    try {
      List<Auth> users = namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSet(resultSet));

      Auth authUser = null;
      if (users.size() > 0) {
        authUser = users.get(0);
        if(authUser.getUsername() == null)
          return ADMIN_USER;
        else
          return GENERAL_USER;
      }

      return INVALID;
    } catch(Exception e) {
      System.out.println(e);
      return INVALID;
    }
  }

  @Override
  public int signUp(Auth auth, String username) {
    UsersDAS usersDAS = new UsersDAS(namedParameterJdbcTemplate);
    usersDAS.insert(new Users(auth.getEmail(), auth.getPassword()));

    GeneralUserDAS generalUserDAS = new GeneralUserDAS(namedParameterJdbcTemplate);
    generalUserDAS.insert(new GeneralUser(username, null));

    return 1;
  }

  private static Auth formatResultSet(ResultSet rs) throws SQLException {
    String username = rs.getString("username");
    if(username != null)
      username = username.trim();

    return new Auth(
            rs.getInt("uid"),
            rs.getString("email").trim(),
            rs.getString("password").trim(),
            username
    );
  }
}