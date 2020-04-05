package com.atlas.dao;

import com.atlas.model.UserReview;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserReviewDAO {
    int insert(UserReview userReview);

    int update(int id, UserReview userReview);

    int delete(int id);

    Optional<UserReview> get(int id);

    List<UserReview> getAll();

    List<Map<String, Object>> getAllByGid(int id);
}
