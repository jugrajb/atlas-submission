package com.atlas.das;

import com.atlas.dao.WorkedOnDAO;
import com.atlas.model.WorkedOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-workedon")
public class WorkedOnDAS implements WorkedOnDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public WorkedOnDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(WorkedOn workedOn) {
        final String sql = "INSERT INTO WorkedOn (gid, pid, role) VALUES (:gid, :pid, :role)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", workedOn.getGid());
        args.addValue("pid", workedOn.getPid());
        args.addValue("role", workedOn.getRole());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int gid, int pid, WorkedOn workedOn) {
        final String sql = "UPDATE WorkedOn SET gid = :gid_new, pid = :pid_new, role = :role " +
                           "WHERE gid = :gid_old AND pid = :pid_old";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid_old", gid);
        args.addValue("pid_old", pid);

        args.addValue("gid_new", workedOn.getGid());
        args.addValue("pid_new", workedOn.getPid());
        args.addValue("role", workedOn.getRole());
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int gid, int pid) {
        final String sql = "DELETE FROM WorkedOn WHERE gid = :gid AND pid = :pid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        args.addValue("pid", pid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<WorkedOn> get(int gid, int pid) {
        final String sql = "SELECT * FROM WorkedOn WHERE gid = :gid AND pid = :pid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        args.addValue("pid", pid);
        List<WorkedOn> workedOns = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        WorkedOn workedOn = null;

        if (workedOns.size() > 0) {
            workedOn = workedOns.get(0);
        }

        return Optional.ofNullable(workedOn);
    }

    @Override
    public List<WorkedOn> getAll() {
        final String sql = "SELECT * FROM WorkedOn";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private WorkedOn formatResultSet(ResultSet rs) throws SQLException {
        String role = rs.getString("role");
        if (role != null) {
            role = role.trim();
        }
        return new WorkedOn(
                rs.getInt("gid"),
                rs.getInt("pid"),
                role
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
