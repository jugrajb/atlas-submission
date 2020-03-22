package com.atlas.das;

import com.atlas.dao.VideoGamePersonDAO;
import com.atlas.model.VideoGamePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-videogameperson")
public class VideoGamePersonDAS implements VideoGamePersonDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public VideoGamePersonDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(VideoGamePerson person) {
        final String sql = "INSERT INTO VideoGamePerson " +
                           "(firstname, lastname, birthdate, sid) " +
                           "VALUES (:firstname, :lastname, :birthdate, :sid)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("firstname", person.getFirstname());
        args.addValue("lastname", person.getLastname());
        args.addValue("birthdate", person.getBirthdate());
        args.addValue("sid", person.getSid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int pid, VideoGamePerson person) {
        final String sql = "UPDATE VideoGamePerson SET " +
                "firstname = :firstname, lastname = :lastname, " +
                "birthdate = :birthdate, sid = :sid " +
                "WHERE pid = :pid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("firstname", person.getFirstname());
        args.addValue("lastname", person.getLastname());
        args.addValue("birthdate", person.getBirthdate());
        args.addValue("sid", person.getSid());
        args.addValue("pid", pid);
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM VideoGamePerson WHERE pid = :pid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", id);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<VideoGamePerson> get(int pid) {
        final String sql = "SELECT * FROM VideoGamePerson WHERE pid = :pid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", pid);

        List<VideoGamePerson> people = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));
        VideoGamePerson person = null;
        if (people.size() > 0)
            person = people.get(0);

        return Optional.ofNullable(person);
    }

    @Override
    public List<VideoGamePerson> getAll() {
        String sql = "SELECT * FROM VideoGamePerson";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static VideoGamePerson formatResultSet(ResultSet rs) throws SQLException {
        return new VideoGamePerson(
                rs.getInt("pid"),
                rs.getString("firstname").trim(),
                rs.getString("lastname").trim(),
                rs.getDate("birthdate"),
                rs.getInt("sid")
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
