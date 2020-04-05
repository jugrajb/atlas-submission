package com.atlas.das;

import com.atlas.dao.GameAwardPairDAO;
import com.atlas.model.GameAwardPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postgres-gameawardpair")
public class GameAwardPairDAS implements GameAwardPairDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GameAwardPairDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<GameAwardPair> get(int gid) {
        final String sql = "SELECT vg.gid, vga.oid, vg.title, vga.name AS award, ao.name AS organization, a.year " +
                "FROM VideoGame vg, Awarded a, VideoGameAward vga, AwardOrganization ao " +
                "WHERE vg.gid = a.gid AND a.name = vga.name AND a.oid = vga.oid " +
                "AND vga.oid = ao.oid AND vg.gid = :gid " +
                "ORDER BY a.year DESC";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        return namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSet(resultSet));
    }

    @Override
    public List<GameAwardPair> getAll() {
        final String sql = "SELECT vg.gid, vga.oid, vg.title, vga.name AS award, ao.name AS organization, a.year " +
                "FROM VideoGame vg, Awarded a, VideoGameAward vga, AwardOrganization ao " +
                "WHERE vg.gid = a.gid AND a.name = vga.name AND a.oid = vga.oid AND vga.oid = ao.oid " +
                "ORDER BY vg.title ASC, a.year DESC";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private GameAwardPair formatResultSet(ResultSet rs) throws SQLException {
        return new GameAwardPair(
                rs.getInt("gid"),
                rs.getInt("oid"),
                rs.getString("title").trim(),
                rs.getString("award").trim(),
                rs.getString("organization").trim(),
                rs.getInt("year")
        );
    }
}
