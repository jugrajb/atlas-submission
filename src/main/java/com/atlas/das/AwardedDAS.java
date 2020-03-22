package com.atlas.das;


import com.atlas.dao.AwardedDAO;
import com.atlas.model.Awarded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-awarded")
public class AwardedDAS implements AwardedDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AwardedDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(Awarded awarded) {
        final String sql = "INSERT INTO Awarded (name, oid, gid, year) VALUES (:name, :oid, :gid, :year)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", awarded.getName());
        args.addValue("oid", awarded.getOid());
        args.addValue("gid", awarded.getGid());
        args.addValue("year", awarded.getYear());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(String name, int oid, int gid, Awarded awarded) {
        final String sql = "UPDATE Awarded SET name = :name_new, oid = :oid_new, gid = :gid_new, year = :year " +
                           "WHERE name = :name_old AND oid = :oid_old AND gid = :gid_old";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name_old", name);
        args.addValue("oid_old", oid);
        args.addValue("gid_old", gid);

        args.addValue("name_new", awarded.getName());
        args.addValue("oid_new", awarded.getOid());
        args.addValue("gid_new", awarded.getGid());
        args.addValue("year", awarded.getYear());

        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(String name, int oid, int gid) {
        final String sql = "DELETE FROM Awarded WHERE name = :name AND oid = :oid AND gid = :gid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", name);
        args.addValue("oid", oid);
        args.addValue("gid", gid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<Awarded> get(String name, int oid, int gid) {
        final String sql = "SELECT * FROM Awarded WHERE name = :name AND oid = :oid AND gid = :gid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", name);
        args.addValue("oid", oid);
        args.addValue("gid", gid);
        List<Awarded> awardeds = namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResults(resultSet));
        Awarded awarded = null;
        if (awardeds.size() > 0) {
            awarded = awardeds.get(0);
        }
        return Optional.ofNullable(awarded);
    }

    @Override
    public List<Awarded> getAll() {
        final String sql = "SELECT * FROM Awarded";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResults(resultSet));
    }

    private Awarded formatResults(ResultSet rs) throws SQLException {
        return new Awarded(
                rs.getString("name").trim(),
                rs.getInt("oid"),
                rs.getInt("gid"),
                rs.getInt("year")
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
