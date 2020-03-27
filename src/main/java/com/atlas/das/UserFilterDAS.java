package com.atlas.das;

import com.atlas.dao.UserFilterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("postgres-userfilter")
public class UserFilterDAS implements UserFilterDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserFilterDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Map<String, Object>> get(int id, List<String> columns) {
        String fields = String.join(", ", columns);
        final String sql = String.format("SELECT %s FROM Users NATURAL JOIN GeneralUser WHERE uid = :uid", fields);
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", id);
        List<Map<String, Object>> results = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet, columns));

        Map<String, Object> result = null;
        if (results.size() > 0) result = results.get(0);

        return Optional.ofNullable(result);
    }

    @Override
    public List<Map<String, Object>> getAll(List<String> columns) {
        String fields = String.join(", ", columns);
        final String sql = String.format("SELECT %s FROM Users NATURAL JOIN GeneralUser", fields);
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet, columns));
    }

    private Map<String, Object> formatResultSet(ResultSet rs, List<String> columns) throws SQLException {
        Map<String, Object> obj = new HashMap<>();
        for (String col : columns) {
            Object item = rs.getObject(col);
            if (item instanceof String)
                item = ((String) item).trim();
            obj.put(col, item);
        }
        return obj;
    }
}
