package com.atlas.das;

import com.atlas.dao.CriticReviewDAO;
import com.atlas.model.CriticReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("postgres-criticreview")
public class CriticReviewDAS implements CriticReviewDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CriticReviewDAS(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(CriticReview criticReview) {
        final String sql =
                "INSERT INTO criticReview " +
                        "(gid, title, review, score, author, url, date) " +
                        "VALUES ((gid, title, review, score, author, url, date))";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", criticReview.getGid());
        args.addValue("title", criticReview.getTitle());
        args.addValue("review", criticReview.getReview());
        args.addValue("score", criticReview.getScore());
        args.addValue("author", criticReview.getAuthor());
        args.addValue("url", criticReview.getUrl());
        args.addValue("date", criticReview.getDate());

        try {
            namedParameterJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    @Override
    public int update(int id, CriticReview criticReview) {
        final String sql =
                "UPDATE criticReview SET " +
                        "gid = :gid, title = :title, review = :review, " +
                        "score = :score, author = :author, url = :url, date = :date " +
                        "WHERE rid = :rid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("gid", criticReview.getTitle());
        args.addValue("title", criticReview.getTitle());
        args.addValue("review", criticReview.getReview());
        args.addValue("score", criticReview.getScore());
        args.addValue("author", criticReview.getAuthor());
        args.addValue("url", criticReview.getUrl());
        args.addValue("date", criticReview.getDate());
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
        final String sql = "DELETE FROM criticReview WHERE rid = :rid";

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
    public Optional<CriticReview> get(int id) {
        final String sql  = "SELECT * FROM criticReview WHERE rid = :rid";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("rid", id);

        List<CriticReview> reviews = namedParameterJdbcTemplate.query(sql, args, ((resultSet, i) -> formatResultSet(resultSet)));

        CriticReview review = null;

        if(reviews.size() > 0)
            review = reviews.get(0);

        return Optional.ofNullable(review);
    }

    @Override
    public List<CriticReview> getAll() {
        final String sql  = "SELECT * FROM criticReview";
        return namedParameterJdbcTemplate.query(sql, (resultSet, i) -> formatResultSet(resultSet));
    }

  @Override
  public List<CriticReview> getAllByGid(int id) {
    final String sql  = "SELECT * FROM criticReview WHERE gid = :gid";

    MapSqlParameterSource args = new MapSqlParameterSource();
    args.addValue("gid", id);

    return namedParameterJdbcTemplate.query(sql, args, (resultSet, i) -> formatResultSet(resultSet));
  }

  private static CriticReview formatResultSet(ResultSet resultSet) throws SQLException {
        return new CriticReview(
                Integer.parseInt(resultSet.getString("rid").trim()),
                Integer.parseInt(resultSet.getString("gid").trim()),
                resultSet.getString("title").trim(),
                resultSet.getString("review").trim(),
                Integer.parseInt(resultSet.getString("score").trim()),
                resultSet.getString("author").trim(),
                resultSet.getString("url").trim(),
                Date.valueOf(resultSet.getString("date").trim())
        );
    }
}
