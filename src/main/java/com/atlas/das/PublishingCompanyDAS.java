package com.atlas.das;

import com.atlas.dao.PublishingCompanyDAO;
import com.atlas.model.PublishingCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-publishingcompany")
public class PublishingCompanyDAS implements PublishingCompanyDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PublishingCompanyDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(PublishingCompany publishingCompany) {
        final String sql = "INSERT INTO PublishingCompany (name, city, state) VALUES (:name, :city, :state)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", publishingCompany.getName());
        args.addValue("city", publishingCompany.getCity());
        args.addValue("state", publishingCompany.getState());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int pcid, PublishingCompany publishingCompany) {
        final String sql = "UPDATE PublishingCompany SET name = :name, city = :city, state = :city " +
                           "WHERE pcid = :pcid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", publishingCompany.getName());
        args.addValue("city", publishingCompany.getCity());
        args.addValue("state", publishingCompany.getState());
        args.addValue("pcid", pcid);
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int pcid) {
        final String sql = "DELETE FROM PublishingCompany WHERE pcid = :pcid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pcid", pcid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<PublishingCompany> get(int pcid) {
        final String sql = "SELECT * FROM PublishingCompany WHERE pcid = :pcid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pcid", pcid);
        List<PublishingCompany> publishingCompanies = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        PublishingCompany publishingCompany = null;
        if (publishingCompanies.size() > 0) {
            publishingCompany = publishingCompanies.get(0);
        }
        return Optional.ofNullable(publishingCompany);
    }

    @Override
    public List<PublishingCompany> getAll() {
        final String sql = "SELECT * FROM PublishingCompany";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private PublishingCompany formatResultSet(ResultSet rs) throws SQLException {
        return new PublishingCompany(
                rs.getInt("pcid"),
                rs.getString("name").trim(),
                rs.getString("city").trim(),
                rs.getString("state").trim()
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
