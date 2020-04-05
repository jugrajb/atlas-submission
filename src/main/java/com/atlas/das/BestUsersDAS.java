package com.atlas.das;

import com.atlas.dao.BestUsersDAO;
import com.atlas.model.GeneralUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postgres-bestusers")
public class BestUsersDAS implements BestUsersDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BestUsersDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<GeneralUser> getAll() {
        final String sql = "SELECT * " +
                "FROM GeneralUser u " +
                "WHERE NOT EXISTS " +
                "((SELECT DISTINCT r1.gid FROM UserReview r1) EXCEPT " +
                "(SELECT DISTINCT r2.gid FROM UserReview r2 " +
                "WHERE r2.uid = u.uid))";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static GeneralUser formatResultSet(ResultSet resultSet) throws SQLException {
        String profileImage = resultSet.getString("profileImage");
        if (profileImage != null)  {
            profileImage = profileImage.trim();
        }
        return new GeneralUser(
                Integer.parseInt(resultSet.getString("uid").trim()),
                resultSet.getString("username").trim(),
                profileImage
        );
    }
}
