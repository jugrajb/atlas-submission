package com.atlas.das;

import com.atlas.dao.AdministratorDAO;
import com.atlas.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-administrator")
public class AdministratorDAS implements AdministratorDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AdministratorDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(Administrator administrator) {
        final String sql =
                "INSERT INTO administrator " +
                        "(uid, firstname , lastname) " +
                        "VALUES (:uid, :firstname, :lastname)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", administrator.getUid());
        args.addValue("firstname", administrator.getFirstname());
        args.addValue("lastname", administrator.getLastname());

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, Administrator administrator) {
        final String sql =
                "UPDATE administrator SET firstname = :firstname, lastname = :lastname, WHERE uid = :uid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("firstname", administrator.getFirstname());
        args.addValue("lastname", administrator.getLastname());
        args.addValue("uid", id);


        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM administrator WHERE uid = :uid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public Optional<Administrator> get(int id) {
        final String sql  = "SELECT * FROM administrator WHERE uid = :uid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("uid", id);

        List<Administrator> admins = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        Administrator admin = null;

        if(admins.size() > 0)
            admin = admins.get(0);

        return Optional.ofNullable(admin);
    }

    @Override
    public List<Administrator> getAll() {
        final String sql  = "SELECT * FROM administrator";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static Administrator formatResultSet(ResultSet resultSet) throws SQLException {
        return new Administrator(
                Integer.parseInt(resultSet.getString("uid").trim()),
                resultSet.getString("firstname").trim(),
                resultSet.getString("lastname").trim()
        );
    }
}
