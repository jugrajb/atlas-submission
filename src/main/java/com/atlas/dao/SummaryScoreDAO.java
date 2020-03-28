package com.atlas.dao;

import com.atlas.model.SummaryScore;

import java.util.List;
import java.util.Optional;

public interface SummaryScoreDAO {
    Optional<SummaryScore> getUserSummaryById(int id);

    Optional<SummaryScore> getCriticSummaryById(int id);

    List<SummaryScore> getUserSummaryAll();

    List<SummaryScore> getCriticSummaryAll();
}
