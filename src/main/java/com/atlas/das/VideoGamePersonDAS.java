package com.atlas.das;

import com.atlas.dao.VideoGamePersonDAO;
import com.atlas.model.VideoGamePerson;
import org.springframework.beans.factory.annotation.Autowired;
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
        return 0;
    }

    @Override
    public int deleteVideoGamePerson(int id) {
        return 0;
    }

    @Override
    public int updateVideoGamePerson(int pid, VideoGamePerson person) {
        return 0;
    }

    @Override
    public Optional<VideoGamePerson> getVideoGamePerson(int pid) {
        return Optional.empty();
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
