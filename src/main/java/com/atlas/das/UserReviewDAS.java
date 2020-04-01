package com.atlas.das;

import com.atlas.dao.UserReviewDAO;
import com.atlas.model.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-userreview")
public class UserReviewDAS implements UserReviewDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserReviewDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(UserReview userReview) {
        final String sql =
                "INSERT INTO userReview " +
                        "(uid, gid, title, review, score, date) " +
                        "VALUES (:uid, :gid, :title, :review, :score, :date)";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", userReview.getGid());
        args.addValue("title", userReview.getTitle());
        args.addValue("review", userReview.getReview());
        args.addValue("score", userReview.getScore());
        args.addValue("uid", userReview.getUid());
        args.addValue("date", userReview.getDate());

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, UserReview userReview) {
        final String sql =
                "UPDATE userReview SET " +
                        "gid = :gid, uid = :uid, title = :title, review = :review, " +
                        "score = :score, date = :date " +
                        "WHERE rid = :rid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", userReview.getTitle());
        args.addValue("title", userReview.getTitle());
        args.addValue("review", userReview.getReview());
        args.addValue("score", userReview.getScore());
        args.addValue("uid", userReview.getUid());
        args.addValue("date", userReview.getDate());
        args.addValue("rid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int delete(int id) {
        final String sql = "DELETE FROM userReview WHERE rid = :rid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("rid", id);

        try {
            namedParameterJdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public Optional<UserReview> get(int id) {
        final String sql  = "SELECT * FROM userReview WHERE rid = :rid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("rid", id);

        List<UserReview> reviews = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        UserReview review = null;

        if(reviews.size() > 0)
            review = reviews.get(0);

        return Optional.ofNullable(review);
    }

    @Override
    public List<UserReview> getAll() {
        final String sql  = "SELECT * FROM userReview";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

    private static UserReview formatResultSet(ResultSet resultSet) throws SQLException {
        return new UserReview(
                Integer.parseInt(resultSet.getString("rid").trim()),
                Integer.parseInt(resultSet.getString("uid").trim()),
                Integer.parseInt(resultSet.getString("gid").trim()),
                resultSet.getString("title").trim(),
                resultSet.getString("review").trim(),
                Integer.parseInt(resultSet.getString("score").trim()),
                Date.valueOf(resultSet.getString("date").trim())
        );
    }
}
