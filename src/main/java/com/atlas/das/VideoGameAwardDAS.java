package com.atlas.das;

import com.atlas.dao.VideoGameAwardDAO;
import com.atlas.model.VideoGame;
import com.atlas.model.VideoGameAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-videogameaward")
public class VideoGameAwardDAS implements VideoGameAwardDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public VideoGameAwardDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(VideoGameAward videoGameAward) {
        final String sql = "INSERT INTO VideoGameAward (name, oid) VALUES (:name, :oid)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", videoGameAward.getName());
        args.addValue("oid", videoGameAward.getOid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(String name, int oid, VideoGameAward videoGameAward) {
        final String sql = "UPDATE VideoGameAward SET name = :name_new, oid = :oid_new " +
                           "WHERE name = :name_old AND oid = :oid_old";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name_old", name);
        args.addValue("oid_old", oid);
        args.addValue("name_new", videoGameAward.getName());
        args.addValue("oid_new", videoGameAward.getOid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(String name, int oid) {
        final String sql = "DELETE FROM VideoGameAward WHERE name = :name AND oid = :oid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", name);
        args.addValue("oid", oid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<VideoGameAward> get(String name, int oid) {
        final String sql = "SELECT * FROM VideoGameAward WHERE name = :name AND oid = :oid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", name);
        args.addValue("oid", oid);
        List<VideoGameAward> videoGameAwards = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        VideoGameAward videoGameAward = null;
        if (videoGameAwards.size() > 0) {
            videoGameAward = videoGameAwards.get(0);
        }
        return Optional.ofNullable(videoGameAward);
    }

    @Override
    public List<VideoGameAward> getAll() {
        final String sql = "SELECT * FROM VideoGameAward";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private VideoGameAward formatResultSet(ResultSet rs) throws SQLException {
        return new VideoGameAward(
                rs.getString("name").trim(),
                rs.getInt("oid")
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
