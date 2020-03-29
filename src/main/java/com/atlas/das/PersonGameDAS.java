package com.atlas.das;

import com.atlas.dao.PersonGameDAO;
import com.atlas.model.GameByPerson;
import com.atlas.model.PersonByGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postgres-persongame")
public class PersonGameDAS implements PersonGameDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PersonGameDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<GameByPerson> getGamesByPerson(int pid) {
        final String sql = "SELECT vg.gid, vg.title, vg.releaseDate " +
                           "FROM VideoGame vg " +
                           "WHERE vg.gid IN  " +
                           "(SELECT w.gid FROM WorkedOn w WHERE w.pid = :pid)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", pid);
        return namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSetGame(resultSet));
    }

    @Override
    public List<PersonByGame> getPeopleByGame(int gid) {
        final String sql = "SELECT p.pid, p.firstname, p.lastname, w.role " +
                           "FROM VideoGamePerson p, WorkedOn w, VideoGame vg " +
                           "WHERE w.pid = p.pid AND w.gid = vg.gid AND vg.gid = :gid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        return namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSetPerson(resultSet));
    }

    private GameByPerson formatResultSetGame(ResultSet rs) throws SQLException {
        return new GameByPerson(
          rs.getInt("gid"),
          rs.getString("title").trim(),
          rs.getDate("releaseDate")
        );
    }

    private PersonByGame formatResultSetPerson(ResultSet rs) throws SQLException {
        String role = rs.getString("role");
        if (role != null) role = role.trim();

        return new PersonByGame(
            rs.getInt("pid"),
            rs.getString("firstname").trim(),
            rs.getString("lastname").trim(),
            role
        );
    }
}
