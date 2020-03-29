package com.atlas.das;

import com.atlas.dao.GeneralUserDAO;
import com.atlas.model.GeneralUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-generaluser")
public class GeneralUserDAS implements GeneralUserDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GeneralUserDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(GeneralUser generalUser) {
        final String sql =
                "INSERT INTO generalUser " +
                        "(uid, username, profileImage) " +
                        "VALUES (:uid, :username, :profileImage)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", generalUser.getUid());
        args.addValue("firstname", generalUser.getUsername());
        args.addValue("lastname", generalUser.getProfileImage());

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, GeneralUser generalUser) {
        final String sql =
                "UPDATE generalUser SET username = :uername, profileImage = :profileImage, WHERE uid = :uid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("username", generalUser.getUsername());
        args.addValue("profileImage", generalUser.getProfileImage());
        args.addValue("uid", id);


        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM generalUser WHERE uid = :uid";

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
    public Optional<GeneralUser> get(int id) {
        final String sql  = "SELECT * FROM generalUser WHERE uid = :uid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", id);

        List<GeneralUser> users = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        GeneralUser user = null;

        if(users.size() > 0)
            user = users.get(0);

        return Optional.ofNullable(user);
    }

    @Override
    public List<GeneralUser> getAll() {
        final String sql  = "SELECT * FROM generalUser";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static GeneralUser formatResultSet(ResultSet resultSet) throws SQLException {
        byte[] bytes = resultSet.getBytes("profileImage");
        ByteArrayResource bar = null;
        if (bytes != null)  {
            bar = new ByteArrayResource(bytes);
        }
        return new GeneralUser(
                Integer.parseInt(resultSet.getString("uid").trim()),
                resultSet.getString("username").trim(),
                bar
        );
    }
}
