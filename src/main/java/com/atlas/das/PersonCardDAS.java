package com.atlas.das;

import com.atlas.dao.PersonCardDAO;
import com.atlas.model.Condition;
import com.atlas.model.PersonCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public List<PersonCard> getWithCondition(Condition condition) {
        String sql = "SELECT pid, firstname, lastname FROM VideoGamePerson " +
                "WHERE %s %s :value " +
                "ORDER BY firstname, lastname ASC";
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

    private PersonCard formatResultSet(ResultSet rs) throws SQLException {
        return new PersonCard(
                rs.getInt("pid"),
                rs.getString("firstname").trim(),
                rs.getString("lastname").trim()
        );
    }
}
