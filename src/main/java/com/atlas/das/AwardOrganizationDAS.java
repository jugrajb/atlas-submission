package com.atlas.das;

import com.atlas.dao.AwardOrganizationDAO;
import com.atlas.model.AwardOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-awardorganization")
public class AwardOrganizationDAS implements AwardOrganizationDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AwardOrganizationDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(AwardOrganization awardOrganization) {
        final String sql = "INSERT INTO AwardOrganization (name, websiteUrl) VALUES (:name, :websiteUrl)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", awardOrganization.getName());
        args.addValue("websiteUrl", awardOrganization.getWebsiteUrl());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int oid, AwardOrganization awardOrganization) {
        final String sql = "UPDATE AwardOrganization SET name = :name, websiteUrl = :websiteUrl " +
                           "WHERE oid = :oid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("oid", oid);
        args.addValue("name", awardOrganization.getName());
        args.addValue("websiteUrl", awardOrganization.getWebsiteUrl());
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int oid) {
        final String sql = "DELETE FROM AwardOrganization WHERE oid = :oid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("oid", oid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<AwardOrganization> get(int oid) {
        final String sql = "SELECT * FROM AwardOrganization WHERE oid = :oid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("oid", oid);
        List<AwardOrganization> aoList = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));
        AwardOrganization ao = null;
        if (aoList.size() > 0) {
            ao = aoList.get(0);
        }
        return Optional.ofNullable(ao);
    }

    @Override
    public List<AwardOrganization> getAll() {
        final String sql = "SELECT * FROM AwardOrganization";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private AwardOrganization formatResultSet(ResultSet rs) throws SQLException {
        return new AwardOrganization(
                rs.getInt("oid"),
                rs.getString("name").trim(),
                rs.getString("websiteUrl").trim()
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
