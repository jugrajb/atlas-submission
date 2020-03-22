package com.atlas.das;

import com.atlas.dao.DirectorDAO;
import com.atlas.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-director")
public class DirectorDAS implements DirectorDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DirectorDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(Director director) {
        final String sql =
                "INSERT INTO director " +
                        "(pid, specialization ) " +
                        "VALUES (:pid, :specialization)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", director.getPid());
        args.addValue("specialization ", director.getSpecialization());

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, Director director) {
        final String sql =
                "UPDATE director SET specialization = :specialization WHERE pid = :pid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("specialization ", director.getSpecialization());
        args.addValue("pid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM director WHERE pid = :pid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public Optional<Director> get(int id) {
        final String sql  = "SELECT * FROM director WHERE pid = :pid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", id);

        List<Director> directors = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        Director director = null;

        if(directors.size() > 0)
            director = directors.get(0);

        return Optional.ofNullable(director);
    }

    @Override
    public List<Director> getAll() {
        final String sql  = "SELECT * FROM director";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static Director formatResultSet(ResultSet resultSet) throws SQLException {
        return new Director(
                Integer.parseInt(resultSet.getString("pid").trim()),
                resultSet.getString("specialization").trim()
        );
    }
}
