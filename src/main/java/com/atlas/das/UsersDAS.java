package com.atlas.das;

import com.atlas.dao.UsersDAO;
import com.atlas.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-users")
public class UsersDAS implements UsersDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UsersDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(Users users) {
        final String sql =
                "INSERT INTO users " +
                        "(email , password) " +
                        "VALUES (:email, :password)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("email", users.getEmail());
        args.addValue("password", users.getPassword());

        if(users.getPassword().length() == 0)
            return 0;

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, Users users) {
        final String sql =
                "UPDATE users SET email = :email, password = :password WHERE uid = :uid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("email", users.getEmail());
        args.addValue("password", users.getPassword());
        args.addValue("uid", id);


        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM users WHERE uid = :uid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public Optional<Users> get(int id) {
        final String sql  = "SELECT * FROM users WHERE uid = :uid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", id);

        List<Users> users = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        Users user = null;

        if(users.size() > 0)
            user = users.get(0);

        return Optional.ofNullable(user);
    }

    public Optional<Users> getByEmail(String email) {
        final String sql = "SELECT * FROM users WHERE email = :email";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("email", email);

        List<Users> users = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        Users user = null;

        if(users.size() > 0)
            user = users.get(0);

        return Optional.ofNullable(user);
    }

    @Override
    public List<Users> getAll() {
        final String sql  = "SELECT * FROM users";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static Users formatResultSet(ResultSet resultSet) throws SQLException {
        return new Users(
                Integer.parseInt(resultSet.getString("uid").trim()),
                resultSet.getString("email").trim(),
                resultSet.getString("password").trim()
        );
    }
}
