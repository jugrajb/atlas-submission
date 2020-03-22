package com.atlas.das;


import com.atlas.dao.VideoGameDAO;
import com.atlas.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-videogame")
public class VideoGameDAS implements VideoGameDAO {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public VideoGameDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public int insert(VideoGame videoGame) {
    final String sql =
            "INSERT INTO videogame " +
            "(title, releaseDate, genre, pcid, sid, pid) " +
            "VALUES (:title, :releaseDate, :genre, :pcid, :sid, :pid)";

    MapSqlParameterSource args = new MapSqlParameterSource();
    args.addValue("title", videoGame.getTitle());
    args.addValue("releaseDate", videoGame.getReleaseDate());
    args.addValue("genre", videoGame.getGenre());
    args.addValue("pcid", videoGame.getPcid());
    args.addValue("sid", videoGame.getSid());
    args.addValue("pid", videoGame.getPid());

    try {
        namedParameterJdbcTemplate.update(sql,args);
    } catch (Exception e) {
      return 0;
    }

    return 1;
  }

  @Override
  public int update(int id, VideoGame videoGame) {
    final String sql =
            "UPDATE videogame SET " +
            "title = :title, releaseDate = :releaseDate, " +
            "genre = :genre, pcid = :pcid, sid = :sid, " +
            "pid = :pid WHERE gid = :gid";

    MapSqlParameterSource args = new MapSqlParameterSource();
    args.addValue("title", videoGame.getTitle());
    args.addValue("releaseDate", videoGame.getReleaseDate());
    args.addValue("genre", videoGame.getGenre());
    args.addValue("pcid", videoGame.getPcid());
    args.addValue("sid", videoGame.getSid());
    args.addValue("pid", videoGame.getPid());
    args.addValue("gid", id);


    try {
      namedParameterJdbcTemplate.update(sql, args);
    } catch (Exception e) {
      return 0;
    }

    return 1;
  }

  @Override
  public int delete(int id) {
    final String sql = "DELETE FROM videogame WHERE gid = :gid";

    MapSqlParameterSource args = new MapSqlParameterSource();
    args.addValue("gid", id);

    try {
      namedParameterJdbcTemplate.update(sql, args);
    } catch (Exception e) {
      return 0;
    }

    return 1;
  }

  @Override
  public Optional<VideoGame> get(int id) {
    final String sql  = "SELECT * FROM videogame WHERE gid = :gid";

    MapSqlParameterSource args = new MapSqlParameterSource();
    args.addValue("gid", id);

    List<VideoGame> games = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

    VideoGame game = null;

    if(games.size() > 0)
        game = games.get(0);

    return Optional.ofNullable(game);
  }

  @Override
  public List<VideoGame> getAll() {
    final String sql  = "SELECT * FROM videogame";
    return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
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
