package com.atlas.service;

import com.atlas.dao.UserReviewDAO;
import com.atlas.model.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserReviewService {
    private final UserReviewDAO userReviewDAO;

    @Autowired
    public UserReviewService(@Qualifier("postgres-userreview") UserReviewDAO userReviewDAO) {
        this.userReviewDAO = userReviewDAO;
    }

    public int add(UserReview userReview) {
        return userReviewDAO.insert(userReview);
    }

    public List<UserReview> getAll() {
        return userReviewDAO.getAll();
    }

    public Optional<UserReview> get(int id) {
        return userReviewDAO.get(id);
    }

    public int delete(int id) {
        return userReviewDAO.delete(id);
    }

    public int update(int id, UserReview userReview) {
        return userReviewDAO.update(id, userReview);
    }

    public List<Map<String, Object>> getAllByGid(int id) {return userReviewDAO.getAllByGid(id);}
}
