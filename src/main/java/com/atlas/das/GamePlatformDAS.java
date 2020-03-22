package com.atlas.das;

import com.atlas.dao.GamePlatformDAO;
import com.atlas.model.GamePlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-gameplatform")
public class GamePlatformDAS implements GamePlatformDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GamePlatformDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(GamePlatform gamePlatform) {
        final String sql = "INSERT INTO GamePlatform (name) VALUES (:name)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", gamePlatform.getName());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int gpid, GamePlatform gamePlatform) {
        final String sql = "UPDATE GamePlatform SET name = :name WHERE gpid = :gpid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gpid", gpid);
        args.addValue("name", gamePlatform.getName());
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int gpid) {
        final String sql = "DELETE FROM GamePlatform WHERE gpid = :gpid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gpid", gpid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<GamePlatform> get(int gpid) {
        final String sql = "SELECT * FROM GamePlatform WHERE gpid = :gpid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gpid", gpid);
        List<GamePlatform> gamePlatforms = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        GamePlatform gamePlatform = null;
        if (gamePlatforms.size() > 0) {
            gamePlatform = gamePlatforms.get(0);
        }
        return Optional.ofNullable(gamePlatform);
    }

    @Override
    public List<GamePlatform> getAll() {
        final String sql = "SELECT * FROM GamePlatform";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private GamePlatform formatResultSet(ResultSet rs) throws SQLException {
        return new GamePlatform(
                rs.getInt("gpid"),
                rs.getString("name").trim()
        );
    }

    private int sqlUpdate(String sql, MapSqlParameterSource args) {
        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 1;
    }
}
