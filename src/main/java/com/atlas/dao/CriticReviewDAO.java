package com.atlas.dao;

import com.atlas.model.CriticReview;

import java.util.List;
import java.util.Optional;

public interface CriticReviewDAO {
    int insert(CriticReview criticReview);

    int update(int id, CriticReview criticReview);

    int delete(int id);

    Optional<CriticReview> get(int id);

    List<CriticReview> getAll();

    List<CriticReview> getAllByGid(int id);
}
