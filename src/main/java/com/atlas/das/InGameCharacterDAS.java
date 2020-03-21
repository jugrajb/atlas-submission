package com.atlas.das;

import com.atlas.dao.InGameCharacterDAO;
import com.atlas.model.InGameCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-ingamecharacter")
public class InGameCharacterDAS implements InGameCharacterDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public InGameCharacterDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(InGameCharacter character) {
        final String sql = "INSERT INTO InGameCharacter (name) VALUES (:name)";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", character.getName());
        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 1;
    }

    @Override
    public int update(int id, InGameCharacter character) {
        final String sql = "UPDATE InGameCharacter SET name = :name WHERE cid = :cid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("name", character.getName());
        args.addValue("cid", id);
        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM InGameCharacter WHERE cid = :cid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("cid", id);
        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 1;
    }

    @Override
    public Optional<InGameCharacter> get(int id) {
        final String sql = "SELECT * FROM InGameCharacter WHERE cid = :cid";
        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("cid", id);
        List<InGameCharacter> characters = namedParameterJdbcTemplate.query(
                sql,
                args,
                (resultSet, i) -> formatResultSet(resultSet));

        InGameCharacter character = null;
        if (characters.size() > 0) {
            character = characters.get(0);
        }
        return Optional.ofNullable(character);
    }

    @Override
    public List<InGameCharacter> getAll() {
        final String sql = "SELECT * FROM InGameCharacter";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private InGameCharacter formatResultSet(ResultSet rs) throws SQLException {
        return new InGameCharacter(
                rs.getInt("cid"),
                rs.getString("name")
        );
    }
}
