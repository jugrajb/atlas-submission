package com.atlas.das;


import com.atlas.dao.VideoGameDAO;
import com.atlas.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class VideoGameDataAccessService implements VideoGameDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public VideoGameDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int insertVideoGame(VideoGame videoGame) {

    final String sql = "INSERT INTO videogame (title, releaseDate, genre, pcid, sid, pid) VALUES (?, ?, ?, ?, ?, ?)";

    try {
      jdbcTemplate.update(
              sql,
              videoGame.getTitle(),
              videoGame.getReleaseDate(),
              videoGame.getGenre(),
              videoGame.getPcid(),
              videoGame.getSid(),
              videoGame.getPid()
      );
    } catch (Exception e) {
      return 0;
    }

    return 1;
  }

  @Override
  public int updateVideoGame(int id, VideoGame videoGame) {
    final String sql = "UPDATE videogame SET title = ?, releaseDate = ?, genre = ?, pcid = ?, sid = ?, pid = ? WHERE gid = ?";

    try {
      jdbcTemplate.update(
              sql,
              videoGame.getTitle(),
              videoGame.getReleaseDate(),
              videoGame.getGenre(),
              videoGame.getPcid(),
              videoGame.getSid(),
              videoGame.getPid(),
              id
      );
    } catch (Exception e) {
      return 0;
    }

    return 1;
  }

  @Override
  public int deleteVideoGame(int id) {
    final String sql = "DELETE FROM videogame WHERE gid = ?";

    try {
      jdbcTemplate.update(sql, id);
    } catch (Exception e) {
      return 0;
    }

    return 1;
  }

  @Override
  public Optional<VideoGame> getVideoGame(int id) {
    final String sql  = "SELECT * FROM videogame WHERE gid = ?";
    VideoGame game = jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> formatResultSet(resultSet)));
    return Optional.ofNullable(game);
  }

  @Override
  public List<VideoGame> getAllVideoGame() {
    final String sql  = "SELECT * FROM videogame";
    return jdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
  }

  private static VideoGame formatResultSet(ResultSet resultSet) throws SQLException {
    return new VideoGame(
            Integer.parseInt(resultSet.getString("gid").trim()),
            resultSet.getString("title").trim(),
            Date.valueOf(resultSet.getString("releaseDate").trim()),
            resultSet.getString("genre").trim(),
            Integer.parseInt(resultSet.getString("pcid").trim()),
            Integer.parseInt(resultSet.getString("sid").trim()),
            Integer.parseInt(resultSet.getString("pid").trim())
    );
  }
}
