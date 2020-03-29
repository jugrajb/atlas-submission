package com.atlas.das;

import com.atlas.dao.CharacterVaDAO;
import com.atlas.model.CharacterVa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postgres-characterva")
public class CharacterVaDAS implements CharacterVaDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CharacterVaDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CharacterVa> getById(int gid) {
        final String sql = "SELECT vg.gid, c.cid, p.pid, vg.title,c.name AS character, p.firstname, p.lastname " +
                "FROM VideoGame vg, InGameCharacter c, AppearsIn a, " +
                "VoicePerformanceBy vp, VoiceActor va, VideoGamePerson p " +
                "WHERE vg.gid = a.gid AND c.cid = a.cid AND vp.pid = va.pid AND " +
                "vp.cid = c.cid AND va.pid = p.pid AND vg.gid = :gid " +
                "ORDER BY c.name ASC";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        return namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSet(resultSet));
    }

    @Override
    public List<CharacterVa> getAll() {
        final String sql = "SELECT vg.gid, c.cid, p.pid, vg.title,c.name AS character, p.firstname, p.lastname " +
                "FROM VideoGame vg, InGameCharacter c, AppearsIn a, " +
                "VoicePerformanceBy vp, VoiceActor va, VideoGamePerson p " +
                "WHERE vg.gid = a.gid AND c.cid = a.cid AND vp.pid = va.pid AND " +
                "vp.cid = c.cid AND va.pid = p.pid " +
                "ORDER BY vg.title, c.name ASC ";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private CharacterVa formatResultSet(ResultSet rs) throws SQLException {
        return new CharacterVa(
                rs.getInt("gid"),
                rs.getInt("cid"),
                rs.getInt("pid"),
                rs.getString("title").trim(),
                rs.getString("character").trim(),
                rs.getString("firstname").trim(),
                rs.getString("lastname").trim()
        );
    }
}
