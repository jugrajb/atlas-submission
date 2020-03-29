package com.atlas.das;

import com.atlas.dao.SummaryScoreDAO;
import com.atlas.model.SummaryScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-summaryscore")
public class SummaryScoreDAS  implements SummaryScoreDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public SummaryScoreDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<SummaryScore> getSummaryUserById(int id) {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                "MIN(r.score) min, COUNT(r.rid) count " +
                "FROM VideoGame vg LEFT OUTER JOIN UserReview r " +
                "ON r.gid = vg.gid " +
                "WHERE vg.gid = :gid " +
                "GROUP BY vg.gid, vg.title";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", id);
        return getFirst(sql, args);
    }

    @Override
    public Optional<SummaryScore> getSummaryCriticById(int id) {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                "MIN(r.score) min, COUNT(r.rid) count " +
                "FROM VideoGame vg LEFT OUTER JOIN CriticReview r " +
                "ON r.gid = vg.gid " +
                "WHERE vg.gid = :gid " +
                "GROUP BY vg.gid, vg.title";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", id);
        return getFirst(sql, args);
    }

    @Override
    public List<SummaryScore> getSummaryUserAll() {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                "MIN(r.score) min, COUNT(r.rid) count " +
                "FROM VideoGame vg LEFT OUTER JOIN UserReview r " +
                "ON r.gid = vg.gid " +
                "GROUP BY vg.gid, vg.title " +
                "ORDER BY vg.gid ";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    @Override
    public List<SummaryScore> getSummaryCriticAll() {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                "MIN(r.score) min, COUNT(r.rid) count " +
                "FROM VideoGame vg LEFT OUTER JOIN CriticReview r " +
                "ON r.gid = vg.gid " +
                "GROUP BY vg.gid, vg.title " +
                "ORDER BY vg.gid ";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    @Override
    public Optional<SummaryScore> getSummaryUserBest() {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                "MIN(r.score) min, COUNT(r.rid) count " +
                "FROM VideoGame vg, UserReview r " +
                "WHERE r.gid = vg.gid " +
                "GROUP BY vg.gid, vg.title " +
                "HAVING AVG(r.score) >= ALL " +
                "(SELECT AVG(r.score) " +
                " FROM VideoGame vg, UserReview r " +
                " WHERE r.gid = vg.gid " +
                " GROUP BY vg.gid) ";
        return getFirst(sql, null);

    }

    @Override
    public Optional<SummaryScore> getSummaryCriticBest() {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                "MIN(r.score) min, COUNT(r.rid) count " +
                "FROM VideoGame vg, CriticReview r " +
                "WHERE r.gid = vg.gid " +
                "GROUP BY vg.gid, vg.title " +
                "HAVING AVG(r.score) >= ALL " +
                "(SELECT AVG(r.score) " +
                " FROM VideoGame vg, CriticReview r " +
                " WHERE r.gid = vg.gid " +
                " GROUP BY vg.gid) ";
        return getFirst(sql, null);
    }

    private Optional<SummaryScore> getFirst(String sql, MapSqlParameterSource args) {
        if (args == null) args = new MapSqlParameterSource();

        List<SummaryScore> summaryScoreList = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        SummaryScore summaryScore = null;
        if (summaryScoreList.size() > 0) summaryScore = summaryScoreList.get(0);

        return Optional.ofNullable(summaryScore);
    }

    private SummaryScore formatResultSet(ResultSet rs) throws SQLException {
        return new SummaryScore(
                rs.getInt("gid"),
                rs.getString("title").trim(),
                rs.getDouble("avg"),
                rs.getDouble("max"),
                rs.getDouble("min"),
                rs.getInt("count")
        );
    }
}
