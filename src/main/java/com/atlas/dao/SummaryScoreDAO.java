package com.atlas.dao;

import com.atlas.model.SummaryScore;

import java.util.List;
import java.util.Optional;

public interface SummaryScoreDAO {
    Optional<SummaryScore> getSummaryUserById(int id);

    Optional<SummaryScore> getSummaryCriticById(int id);

    List<SummaryScore> getSummaryUserAll();

    List<SummaryScore> getSummaryCriticAll();

    Optional<SummaryScore> getSummaryUserBest();

    Optional<SummaryScore> getSummaryCriticBest();
}
