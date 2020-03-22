package com.atlas.das;

import com.atlas.dao.VoiceActorDAO;
import com.atlas.model.VoiceActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-voiceactor")
public class VoiceActorDAS implements VoiceActorDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public VoiceActorDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(VoiceActor voiceActor) {
        final String sql =
                "INSERT INTO voiceActor " +
                        "(pid, primaryLanguage ) " +
                        "VALUES (:pid, :primaryLanguage)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", voiceActor.getPid());
        args.addValue("primaryLanguage", voiceActor.getPrimaryLanguage());

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, VoiceActor voiceActor) {
        final String sql =
                "UPDATE voiceActor SET primaryLanguage = :primaryLanguage WHERE pid = :pid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("primaryLanguage ", voiceActor.getPrimaryLanguage());
        args.addValue("pid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM voiceActor WHERE pid = :pid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public Optional<VoiceActor> get(int id) {
        final String sql  = "SELECT * FROM voiceActor WHERE pid = :pid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("pid", id);

        List<VoiceActor> voiceActors = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        VoiceActor voiceActor = null;

        if(voiceActors.size() > 0)
            voiceActor = voiceActors.get(0);

        return Optional.ofNullable(voiceActor);
    }

    @Override
    public List<VoiceActor> getAll() {
        final String sql  = "SELECT * FROM voiceActor";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static VoiceActor formatResultSet(ResultSet resultSet) throws SQLException {
        return new VoiceActor(
                Integer.parseInt(resultSet.getString("pid").trim()),
                resultSet.getString("primaryLanguage").trim()
        );
    }
}
