package com.atlas.service;

import com.atlas.dao.SummaryScoreDAO;
import com.atlas.model.SummaryScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SummaryScoreService {
    private SummaryScoreDAO summaryScoreDAO;

    @Autowired
    public SummaryScoreService(@Qualifier("postgres-summaryscore") SummaryScoreDAO summaryScoreDAO) {
        this.summaryScoreDAO = summaryScoreDAO;
    }

    public Optional<SummaryScore> getUserSummaryById(int id) {
        return summaryScoreDAO.getUserSummaryById(id);
    }

    public Optional<SummaryScore> getCriticSummaryById(int id) {
        return summaryScoreDAO.getCriticSummaryById(id);
    }

    public List<SummaryScore> getUserSummaryAll() {
        return summaryScoreDAO.getUserSummaryAll();
    }

    public List<SummaryScore> getCriticSummaryAll() {
        return summaryScoreDAO.getCriticSummaryAll();
    }
}
