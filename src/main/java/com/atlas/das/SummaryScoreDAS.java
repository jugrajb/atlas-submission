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
    public Optional<SummaryScore> getUserSummaryById(int id) {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score), MAX(r.score), " +
                           "MIN(r.score), COUNT(r.rid) " +
                           "FROM VideoGame vg LEFT OUTER JOIN UserReview r " +
                           "ON r.gid = vg.gid " +
                           "WHERE vg.gid = :gid " +
                           "GROUP BY vg.gid, vg.title";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", id);
        List<SummaryScore> summaryScoreList = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        SummaryScore summaryScore = null;
        if (summaryScoreList.size() > 0) summaryScore = summaryScoreList.get(0);

        return Optional.ofNullable(summaryScore);
    }

    @Override
    public Optional<SummaryScore> getCriticSummaryById(int id) {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                "MIN(r.score) min, COUNT(r.rid) count " +
                "FROM VideoGame vg LEFT OUTER JOIN CriticReview r " +
                "ON r.gid = vg.gid " +
                "WHERE vg.gid = :gid " +
                "GROUP BY vg.gid, vg.title";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", id);
        List<SummaryScore> summaryScoreList = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        SummaryScore summaryScore = null;
        if (summaryScoreList.size() > 0) summaryScore = summaryScoreList.get(0);

        return Optional.ofNullable(summaryScore);
    }

    @Override
    public List<SummaryScore> getUserSummaryAll() {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                           "MIN(r.score) min, COUNT(r.rid) count " +
                           "FROM VideoGame vg LEFT OUTER JOIN UserReview r " +
                           "ON r.gid = vg.gid " +
                           "GROUP BY vg.gid, vg.title " +
                           "ORDER BY vg.gid ";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    @Override
    public List<SummaryScore> getCriticSummaryAll() {
        final String sql = "SELECT vg.gid, vg.title, AVG(r.score) avg, MAX(r.score) max, " +
                "MIN(r.score) min, COUNT(r.rid) count " +
                "FROM VideoGame vg LEFT OUTER JOIN CriticReview r " +
                "ON r.gid = vg.gid " +
                "GROUP BY vg.gid, vg.title " +
                "ORDER BY vg.gid ";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
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
