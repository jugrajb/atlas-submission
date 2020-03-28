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

    public Optional<SummaryScore> getSummaryUserById(int id) {
        return summaryScoreDAO.getSummaryUserById(id);
    }

    public Optional<SummaryScore> getSummaryCriticById(int id) {
        return summaryScoreDAO.getSummaryCriticById(id);
    }

    public List<SummaryScore> getSummaryUserAll() {
        return summaryScoreDAO.getSummaryUserAll();
    }

    public List<SummaryScore> getSummaryCriticAll() {
        return summaryScoreDAO.getSummaryCriticAll();
    }

    public Optional<SummaryScore> getSummaryUserBest() {
        return summaryScoreDAO.getSummaryUserBest();
    }

    public Optional<SummaryScore> getSummaryCriticBest() {
        return summaryScoreDAO.getSummaryCriticBest();
    }


}
