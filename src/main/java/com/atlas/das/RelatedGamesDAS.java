package com.atlas.das;

import com.atlas.dao.RelatedGamesDAO;
import com.atlas.model.GameCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postgres-relatedgames")
public class RelatedGamesDAS implements RelatedGamesDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public RelatedGamesDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<GameCard> get(int gid) {
        final String sql = "SELECT vg.gid, vg.title " +
                "FROM VideoGame vg, Series s WHERE s.gid = vg.gid AND s.prevGid = :gid " +
                "UNION " +
                "SELECT vg.gid, vg.title " +
                "FROM VideoGame vg, Series s WHERE s.prevGid = vg.gid AND s.gid = :gid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        return namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSet(resultSet));
    }

    private GameCard formatResultSet(ResultSet rs) throws SQLException {
        return new GameCard(
          rs.getInt("gid"),
          rs.getString("title").trim()
        );
    }
}
