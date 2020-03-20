package com.atlas.das;


import com.atlas.dao.VideoGameDAO;
import com.atlas.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Types;
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
    Object values = new Object[]{
            videoGame.getTitle(),
            Date.valueOf(videoGame.getReleaseDate()),
            videoGame.getGenre(),
            videoGame.getPcid(),
            videoGame.getSid(),
            videoGame.getPid()
    };
    int[] types = new int[]{
            Types.CHAR,
            Types.DATE,
            Types.CHAR,
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER
    };

    try {
      jdbcTemplate.update(sql, values, types);
      return 1;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      // Do nothing
    }

    return 0;
  }

  @Override
  public int updateVideoGame(int id, VideoGame videoGame) {
    final String sql = "UPDATE videogame SET title = ?, releaseDate = ?, genre = ?, pcid = ?, sid = ?, pid = ? WHERE gid = ?";
    Object values = new Object[]{
      videoGame.getTitle(),
      videoGame.getReleaseDate(),
      videoGame.getGenre(),
      videoGame.getPcid(),
      videoGame.getSid(),
      videoGame.getPid(),
      id
    };

    try {
      jdbcTemplate.update(sql, values);
    } catch (Exception e) {
      // Do Nothing
    }

    return 0;
  }

  @Override
  public int deleteVideoGame(int id) {
    final String sql = "DELETE FROM videogame WHERE gid = ?";
    Object values = new Object[]{id};

    try {
      jdbcTemplate.update(sql, values);
      return 1;
    } catch (Exception e) {
      //Do Nothing
    }

    return 0;
  }

  @Override
  public Optional<VideoGame> getVideoGame(int id) {
    final String sql  = "SELECT * FROM videogame WHERE gid = ?";

    VideoGame game = jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> {
      int gid = Integer.parseInt(resultSet.getString("gid").trim());
      String title = resultSet.getString("title").trim();
      String releaseDate = resultSet.getString("releaseDate").trim();
      String genre = resultSet.getString("genre").trim();
      int pcid = Integer.parseInt(resultSet.getString("pcid").trim());
      int sid = Integer.parseInt(resultSet.getString("sid").trim());
      int pid = Integer.parseInt(resultSet.getString("pid").trim());

      return new VideoGame(gid, title, releaseDate, genre, pcid, sid, pid);
    }));

    return Optional.ofNullable(game);
  }

  @Override
  public List<VideoGame> getAllVideoGame() {
    final String sql  = "SELECT * FROM videogame";

    return jdbcTemplate.query(sql, (resultSet, i) -> {
      int gid = Integer.parseInt(resultSet.getString("gid").trim());
      String title = resultSet.getString("title").trim();
      String releaseDate = resultSet.getString("releaseDate").trim();
      String genre = resultSet.getString("genre").trim();
      int pcid = Integer.parseInt(resultSet.getString("pcid").trim());
      int sid = Integer.parseInt(resultSet.getString("sid").trim());
      int pid = Integer.parseInt(resultSet.getString("pid").trim());

      return new VideoGame(gid, title, releaseDate, genre, pcid, sid, pid);
    });
  }
}
