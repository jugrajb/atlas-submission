package com.atlas.das;

import com.atlas.dao.SeriesDAO;
import com.atlas.model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-series")
public class SeriesDAS implements SeriesDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public SeriesDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(Series series) {
        final String sql =
                "INSERT INTO series " +
                        "(gid, prevGid) " +
                        "VALUES (:gid, :prevGid)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", series.getGid());
        args.addValue("prevGid", series.getPrevGid());

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, Series series) {
        final String sql =
                "UPDATE series SET prevGid = :prevGid, WHERE gid = :gid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("prevGid", series.getPrevGid());
        args.addValue("gid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM series WHERE gid = :gid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public Optional<Series> get(int id) {
        final String sql  = "SELECT * FROM series WHERE gid = :gid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", id);

        List<Series> series = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        Series series1 = null;

        if(series.size() > 0)
            series1 = series.get(0);

        return Optional.ofNullable(series1);
    }

    @Override
    public List<Series> getAll() {
        final String sql  = "SELECT * FROM series";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static Series formatResultSet(ResultSet resultSet) throws SQLException {
        return new Series(
                Integer.parseInt(resultSet.getString("gid").trim()),
                Integer.parseInt(resultSet.getString("prevGid").trim())
        );
    }
}
