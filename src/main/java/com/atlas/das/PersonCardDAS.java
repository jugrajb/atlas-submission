package com.atlas.das;

import com.atlas.dao.PersonCardDAO;
import com.atlas.model.Condition;
import com.atlas.model.PersonCard;
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

@Repository("postgres-personcard")
public class PersonCardDAS implements PersonCardDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PersonCardDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<PersonCard> get(int id) {
        final String sql = "SELECT pid, firstname, lastname FROM VideoGamePerson WHERE pid = :pid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", id);
        List<PersonCard> personCardList = namedParameterJdbcTemplate.query(sql, args,
                (resultSet, i) -> formatResultSet(resultSet));

        PersonCard personCard = null;
        if (personCardList.size() > 0) personCard = personCardList.get(0);

        return Optional.ofNullable(personCard);
    }

    @Override
    public List<PersonCard> getAll() {
        final String sql = "SELECT pid, firstname, lastname FROM VideoGamePerson ORDER BY firstname, lastname ASC";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    @Override
    public List<PersonCard> getWithSelection(Selection selection) {
        String sql_select = "SELECT pid, firstname, lastname FROM VideoGamePerson ";
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

    private PersonCard formatResultSet(ResultSet rs) throws SQLException {
        return new PersonCard(
                rs.getInt("pid"),
                rs.getString("firstname").trim(),
                rs.getString("lastname").trim()
        );
    }
}
