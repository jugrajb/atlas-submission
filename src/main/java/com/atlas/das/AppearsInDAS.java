package com.atlas.das;

import com.atlas.dao.AppearsInDAO;
import com.atlas.model.AppearsIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("postgres-appearsin")
public class AppearsInDAS implements AppearsInDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AppearsInDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(AppearsIn appearsIn) {
        final String sql = "INSERT INTO AppearsIn (gid, cid) VALUES (:gid, :cid)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", appearsIn.getGid());
        args.addValue("cid", appearsIn.getCid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int gid, int cid, AppearsIn appearsIn) {
        final String sql = "UPDATE AppearsIn SET gid = :gid_new, cid = :cid_new " +
                           "WHERE gid = :gid_old AND cid = :cid_old";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid_old", gid);
        args.addValue("cid_old", cid);
        args.addValue("gid_new", appearsIn.getGid());
        args.addValue("cid_new", appearsIn.getCid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int gid, int cid) {
        final String sql = "DELETE FROM AppearsIn WHERE gid = :gid AND cid = :cid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        args.addValue("cid", cid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<AppearsIn> get(int gid, int cid) {
        final String sql = "SELECT * FROM AppearsIn WHERE gid = :gid AND cid = :cid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        args.addValue("cid", cid);
        List<AppearsIn> appearsIns = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));
        AppearsIn appearsIn = null;
        if (appearsIns.size() > 0) {
            appearsIn = appearsIns.get(0);
        }
        return Optional.ofNullable(appearsIn);
    }

    @Override
    public List<AppearsIn> getAll() {
        final String sql = "SELECT * FROM AppearsIn";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private AppearsIn formatResultSet(ResultSet rs) throws SQLException {
        return new AppearsIn(
                rs.getInt("gid"),
                rs.getInt("cid")
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
