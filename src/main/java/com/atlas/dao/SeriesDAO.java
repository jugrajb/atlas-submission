package com.atlas.dao;

import com.atlas.model.Series;

import java.util.List;
import java.util.Optional;

public interface SeriesDAO {
    int insert(Series series);

    int update(int id, Series series);

    int delete(int id);

    Optional<Series> get(int id);

    List<Series> getAll();
}
