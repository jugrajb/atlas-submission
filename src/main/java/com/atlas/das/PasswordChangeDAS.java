package com.atlas.das;

import com.atlas.dao.PasswordChangeDAO;
import com.atlas.model.PasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postgres-passwordchange")
public class PasswordChangeDAS implements PasswordChangeDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PasswordChangeDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<PasswordChange> getByUser(int uid) {
        final String sql = "SELECT * FROM PasswordChange WHERE uid = :uid ORDER BY changedon";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", uid);
        return namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSet(resultSet));
    }

    @Override
    public List<PasswordChange> getAll() {
        final String sql = "SELECT * FROM PasswordChange ORDER BY changedon DESC";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private PasswordChange formatResultSet(ResultSet rs) throws SQLException {
        return new PasswordChange(
                rs.getInt("id"),
                rs.getInt("uid"),
                rs.getString("oldPassword").trim(),
                rs.getString("newPassword").trim(),
                rs.getTimestamp("changedOn")
        );
    }
}
