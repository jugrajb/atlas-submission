package com.atlas.service;

import com.atlas.dao.CriticReviewDAO;
import com.atlas.model.CriticReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriticReviewService {
    private final CriticReviewDAO criticReviewDAO;

    @Autowired
    public CriticReviewService(@Qualifier("postgres-criticreview") CriticReviewDAO criticReviewDAO) {
        this.criticReviewDAO = criticReviewDAO;
    }

    public int add(CriticReview criticReview) {
        return criticReviewDAO.insert(criticReview);
    }

    public List<CriticReview> getAll() {
        return criticReviewDAO.getAll();
    }

    public Optional<CriticReview> get(int id) {
        return criticReviewDAO.get(id);
    }

    public int delete(int id) {
        return criticReviewDAO.delete(id);
    }

    public int update(int id, CriticReview criticReview) {
        return criticReviewDAO.update(id, criticReview);
    }
}
