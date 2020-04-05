package com.atlas.das;

import com.amazonaws.services.dynamodbv2.xspec.M;
import com.atlas.dao.NaturalJoinDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("postgres-naturaljoin")
public class NaturalJoinDAS implements NaturalJoinDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public NaturalJoinDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> get(String relation1, String relation2) {
        String sql = "SELECT * FROM %s NATURAL JOIN %s";
        sql = String.format(sql, relation1, relation2);
        try {
            return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResults(resultSet));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Map<String, Object> formatResults(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            String key = rsmd.getColumnLabel(i);
            Object val = rs.getObject(key);
            if (val instanceof String) {
                val = ((String) val).trim();
            }
            map.put(key, val);
        }
        return map;
    }
}
