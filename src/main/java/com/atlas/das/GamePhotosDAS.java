package com.atlas.das;

import com.atlas.dao.GamePhotosDAO;
import com.atlas.model.GamePhotos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-gamephotos")
public class GamePhotosDAS implements GamePhotosDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GamePhotosDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(GamePhotos gamePhotos) {
        final String sql =
                "INSERT INTO gamephotos " +
                        "(gphid, cover, banner) " +
                        "VALUES (:gphid, :cover, :banner)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gphid", gamePhotos.getGphid());
        args.addValue("cover", gamePhotos.getCover());
        args.addValue("banner", gamePhotos.getBanner());

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, GamePhotos gamePhotos) {
        final String sql =
                "UPDATE gamephotos SET cover = :cover, banner = :banner WHERE gphid = :gphid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gphid", id);
        args.addValue("cover", gamePhotos.getCover());
        args.addValue("banner", gamePhotos.getBanner());


        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM gamephotos WHERE gphid = :gphid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gphid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public Optional<GamePhotos> get(int id) {
        final String sql  = "SELECT * FROM gamephotos WHERE gphid = :gphid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gphid", id);

        List<GamePhotos> gamePhotos = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        GamePhotos gamePhoto = null;

        if(gamePhotos.size() > 0)
            gamePhoto = gamePhotos.get(0);

        return Optional.ofNullable(gamePhoto);
    }

    @Override
    public List<GamePhotos> getAll() {
        final String sql  = "SELECT * FROM gamephotos";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static GamePhotos formatResultSet(ResultSet resultSet) throws SQLException {
        String cover = resultSet.getString("cover");
        if (cover != null)  {
            cover = cover.trim();
        }

        String banner = resultSet.getString("banner");
        if (banner != null)  {
            banner = banner.trim();
        }

        return new GamePhotos(
                Integer.parseInt(resultSet.getString("gphid").trim()),
                cover,
                banner
        );
    }
}
