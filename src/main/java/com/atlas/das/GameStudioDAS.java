package com.atlas.das;

import com.atlas.dao.GameStudioDAO;
import com.atlas.model.GameStudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-gamestudio")
public class GameStudioDAS implements GameStudioDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GameStudioDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(GameStudio gameStudio) {
        final String sql = "INSERT INTO GameStudio (name, city, state) VALUES (:name, :city, :state)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", gameStudio.getName());
        args.addValue("city", gameStudio.getCity());
        args.addValue("state", gameStudio.getState());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int sid, GameStudio gameStudio) {
        final String sql = "UPDATE GameStudio SET name = :name, city = :city, state = :state WHERE sid = :sid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", gameStudio.getName());
        args.addValue("city", gameStudio.getCity());
        args.addValue("state", gameStudio.getState());
        args.addValue("sid", sid);
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int sid) {
        final String sql = "DELETE FROM GameStudio WHERE sid = :sid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("sid", sid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<GameStudio> get(int sid) {
        final String sql = "SELECT * FROM GameStudio WHERE sid = :sid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("sid", sid);
        List<GameStudio> gameStudios = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));
        GameStudio gameStudio = null;

        if (gameStudios.size() > 0) {
            gameStudio = gameStudios.get(0);
        }
        return Optional.ofNullable(gameStudio);
    }

    @Override
    public List<GameStudio> getAll() {
        final String sql = "SELECT * FROM GameStudio";
        return namedParameterJdbcTemplate.query(sql,(resultSet, i) -> formatResultSet(resultSet));
    }

    private GameStudio formatResultSet(ResultSet rs) throws SQLException {
        return new GameStudio(
                rs.getInt("sid"),
                rs.getString("name").trim(),
                rs.getString("city").trim(),
                rs.getString("state").trim()
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
