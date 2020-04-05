package com.atlas.das;

import com.atlas.dao.PlayableOnDAO;
import com.atlas.model.GamePlatform;
import com.atlas.model.PlayableOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-playableon")
public class PlayableOnDAS implements PlayableOnDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PlayableOnDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(PlayableOn playableOn) {
        final String sql = "INSERT INTO PlayableOn (gid, gpid) VALUES (:gid, :gpid)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", playableOn.getGid());
        args.addValue("gpid", playableOn.getGpid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int update(int gid, int gpid, PlayableOn playableOn) {
        final String sql = "UPDATE PlayableOn SET gid = :gid_new, gpid = :gpid_new " +
                           "WHERE gid = :gid_old AND gpid = :gpid_old";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid_old", gid);
        args.addValue("gpid_old", gpid);
        args.addValue("gid_new", playableOn.getGid());
        args.addValue("gpid_new", playableOn.getGpid());
        return sqlUpdate(sql, args);
    }

    @Override
    public int delete(int gid, int gpid) {
        final String sql = "DELETE FROM PlayableOn WHERE gid = :gid AND gpid = :gpid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        args.addValue("gpid", gpid);
        return sqlUpdate(sql, args);
    }

    @Override
    public Optional<PlayableOn> get(int gid, int gpid) {
        final String sql = "SELECT * FROM PlayableOn WHERE gid = :gid AND gpid = :gpid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", gid);
        args.addValue("gpid", gpid);

        List<PlayableOn> playableOns = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));
        PlayableOn playableOn = null;
        if (playableOns.size() > 0) {
            playableOn = playableOns.get(0);
        }
        return Optional.ofNullable(playableOn);
    }

    @Override
    public List<PlayableOn> getAll() {
        final String sql = "SELECT * FROM PlayableOn";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

  @Override
  public List<GamePlatform> getAllPlatformsForVideoGame(int gid) {
      final String sql = "SELECT gp.gpid, gp.name FROM PlayableOn p " +
              "LEFT JOIN GamePlatform gp ON gp.gpid = p.gpid " +
              "WHERE p.gid = :gid";

      MapSqlParameterSource args = new MapSqlParameterSource();
      args.addValue("gid", gid);

      List<GamePlatform> platforms = namedParameterJdbcTemplate.query(sql, args,
              (resultSet, i) -> GamePlatformDAS.formatResultSet(resultSet));

      return platforms;
  }

  private PlayableOn formatResultSet(ResultSet rs) throws SQLException {
        return new PlayableOn(
                rs.getInt("gid"),
                rs.getInt("gpid")
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
