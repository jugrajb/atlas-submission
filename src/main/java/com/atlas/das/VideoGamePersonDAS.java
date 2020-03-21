package com.atlas.das;

import com.atlas.dao.VideoGamePersonDAO;
import com.atlas.model.VideoGamePerson;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
=======
>>>>>>> 63dc14c9e2b9b8d11cb97bf987b8c429ba202b6e
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
    public int insertVideoGamePerson(VideoGamePerson person) {
<<<<<<< HEAD
        final String sql = "INSERT INTO VideoGamePerson " +
                           "(firstname, lastname, birthdate, sid) " +
                           "VALUES (:firstname, :lastname, :birthdate, :sid)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("firstname", person.getFirstname());
        args.addValue("lastname", person.getLastname());
        args.addValue("birthdate", person.getBirthdate());
        args.addValue("sid", person.getSid());

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 1;
=======
        return 0;
>>>>>>> 63dc14c9e2b9b8d11cb97bf987b8c429ba202b6e
    }

    @Override
    public int deleteVideoGamePerson(int id) {
<<<<<<< HEAD
        final String sql = "DELETE FROM VideoGamePerson WHERE pid = :pid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", id);
        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }
        return 1;
=======
        return 0;
>>>>>>> 63dc14c9e2b9b8d11cb97bf987b8c429ba202b6e
    }

    @Override
    public int updateVideoGamePerson(int pid, VideoGamePerson person) {
<<<<<<< HEAD
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
        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 1;
=======
        return 0;
>>>>>>> 63dc14c9e2b9b8d11cb97bf987b8c429ba202b6e
    }

    @Override
    public Optional<VideoGamePerson> getVideoGamePerson(int pid) {
<<<<<<< HEAD
        final String sql = "SELECT * FROM VideoGamePerson WHERE pid = :pid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", pid);

        List<VideoGamePerson> people = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));
        VideoGamePerson person = null;
        if (people.size() > 0)
            person = people.get(0);

        return Optional.ofNullable(person);
=======
        return Optional.empty();
>>>>>>> 63dc14c9e2b9b8d11cb97bf987b8c429ba202b6e
    }

    @Override
    public List<VideoGamePerson> getAllVideoGamePeople() {
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
}
