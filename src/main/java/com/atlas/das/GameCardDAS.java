package com.atlas.das;

import com.atlas.dao.GameCardDAO;
import com.atlas.model.Condition;
import com.atlas.model.GameCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-gamecard")
public class GameCardDAS implements GameCardDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GameCardDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<GameCard> get(int id) {
        final String sql = "SELECT gid, title FROM VideoGame WHERE gid = :gid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", id);
        List<GameCard> gameCards = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        GameCard gameCard = null;
        if (gameCards.size() > 0) gameCard = gameCards.get(0);

        return Optional.ofNullable(gameCard);
    }

    @Override
    public List<GameCard> getAll() {
        final String sql = "SELECT gid, title FROM VideoGame ORDER BY title ASC";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    @Override
    public List<GameCard> getWithCondition(Condition condition) {
        String sql = "SELECT gid, title FROM VideoGame " +
                "WHERE %s %s :value " +
                "ORDER BY title ASC";
        sql = String.format(sql, condition.getAttribute(), condition.getComparator());
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("value", condition.getValue());
        try {
            return namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSet(resultSet));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private GameCard formatResultSet(ResultSet rs) throws SQLException {
        return new GameCard(
                rs.getInt("gid"),
                rs.getString("title").trim()
        );
    }
}
