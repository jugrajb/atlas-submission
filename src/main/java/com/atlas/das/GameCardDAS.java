package com.atlas.das;

import com.atlas.dao.GameCardDAO;
import com.atlas.model.Condition;
import com.atlas.model.GameCard;
import com.atlas.model.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<GameCard> getWithSelection(Selection selection) {
        String sql_select = "SELECT gid, title FROM VideoGame ";
        String sql_where = "WHERE %s ";
        String sql_orderby = "ORDER BY %s";

        // parse WHERE Condition
        if (selection.getConditions() == null || selection.getConditions().isEmpty()) {
            sql_where = "";
        } else {
            List<String> conditionStrs = new ArrayList<>();
            List<Condition> conditions = selection.getConditions()
                    .stream()
                    .filter(c -> (c.getAttribute() != null && c.getComparator() != null && c.getValue() != null))
                    .collect(Collectors.toList());
            for (Condition cond : conditions) {
                String value;
                if (cond.getValue() instanceof Number) {
                    value = cond.getValue().toString();
                } else {
                    value = String.format("\'%s\'", cond.getValue().toString());
                }
                conditionStrs.add(cond.getAttribute() + " " + cond.getComparator() + " " + value);
            }
            String require = (selection.getRequire() != null &&
                    selection.getRequire().equals("ANY")) ? " OR " : " AND ";
            sql_where = String.format(sql_where, String.join(require, conditionStrs));
        }
        // parse ORDER BY clause
        if (selection.getSort() == null || selection.getSort().isEmpty()) {
            sql_orderby = "";
        } else {
            List<String> orders = new ArrayList<>();
            for (int i = 0; i < selection.getSort().size(); i++) {
                if (selection.getDirections() == null || i >= selection.getDirections().size()) {
                    orders.add(selection.getSort().get(i) + " ASC");
                } else {
                    orders.add(selection.getSort().get(i) + " " + selection.getDirections().get(i));
                }
            }
            sql_orderby = String.format(sql_orderby, String.join(", ", orders));
        }

        String sql = sql_select + sql_where + sql_orderby;
        try {
            return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
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
