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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
  public Map login(Auth auth) {
    final String sql = "SELECT u.uid, g.username, u.email, u.password FROM users u " +
            "LEFT JOIN administrator a ON u.uid = a.uid " +
            "LEFT JOIN generaluser g ON u.uid = g.uid " +
            "WHERE u.email = :email AND u.password = :password";

    MapSqlParameterSource args = new MapSqlParameterSource();
    args.addValue("email", auth.getEmail());
    args.addValue("password", auth.getPassword());

    Map<String, Object> resp = new HashMap<>();
    try {
      List<Auth> users = namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSet(resultSet));

      Auth authUser = null;
      if (users.size() > 0) {
        authUser = users.get(0);
        resp.put("user", authUser);
        if(authUser.getUsername() == null)
            resp.put("code", ADMIN_USER);
        else
            resp.put("code", GENERAL_USER);
      } else {
          resp.put("code", INVALID);
      }
    } catch(Exception e) {
      System.out.println(e);
      resp.put("code", INVALID);
    }

    return resp;
  }

  @Override
  public int signUp(Auth auth, String username) {
    UsersDAS usersDAS = new UsersDAS(namedParameterJdbcTemplate);
    int ret = usersDAS.insert(new Users(auth.getEmail(), auth.getPassword()));
    if(ret == 0)
        return 0;

    Optional<Users> user = usersDAS.getByEmail(auth.getEmail());

    GeneralUserDAS generalUserDAS = new GeneralUserDAS(namedParameterJdbcTemplate);
    Users newUser = null;
    if(user.isPresent())
        newUser = user.get();

    if(newUser != null) {
        ret = generalUserDAS.insert(new GeneralUser(newUser.getUid(), username, ""));
        if(ret == 0) {
            usersDAS.delete(newUser.getUid());
            return 0;
        }

        return 1;
    }

    return 0;
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