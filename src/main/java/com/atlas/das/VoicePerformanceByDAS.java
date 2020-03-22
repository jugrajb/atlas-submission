package com.atlas.das;

import com.atlas.dao.VoicePerformanceByDAO;
import com.atlas.model.VoicePerformanceBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-voiceperformanceby")
public class VoicePerformanceByDAS implements VoicePerformanceByDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public VoicePerformanceByDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(VoicePerformanceBy voicePerformanceBy) {
        final String sql = "INSERT INTO VoicePerformanceBy (cid, pid) VALUES (:cid, :pid)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("cid", voicePerformanceBy.getCid());
        args.addValue("pid", voicePerformanceBy.getPid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int cid, int pid, VoicePerformanceBy voicePerformanceBy) {
        final String sql = "UPDATE VoicePerformanceBy SET cid = :cid_new, pid = :pid_new " +
                           "WHERE cid = :cid_old AND pid = :pid_old";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("cid_old", cid);
        args.addValue("pid_old", pid);
        args.addValue("cid_new", voicePerformanceBy.getCid());
        args.addValue("pid_new", voicePerformanceBy.getPid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int cid, int pid) {
        final String sql = "DELETE FROM VoicePerformanceBy WHERE cid = :cid AND pid = :pid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("cid", cid);
        args.addValue("pid", pid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<VoicePerformanceBy> get(int cid, int pid) {
        final String sql = "SELECT * FROM VoicePerformanceBy WHERE cid = :cid AND pid = :pid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("cid", cid);
        args.addValue("pid", pid);
        List<VoicePerformanceBy> vps = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        VoicePerformanceBy vp = null;
        if (vps.size() > 0) {
            vp = vps.get(0);
        }

        return Optional.ofNullable(vp);
    }

    @Override
    public List<VoicePerformanceBy> getAll() {
        final String sql = "SELECT * FROM VoicePerformanceBy";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private VoicePerformanceBy formatResultSet(ResultSet rs) throws SQLException {
        return new VoicePerformanceBy(
                rs.getInt("cid"),
                rs.getInt("pid")
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
