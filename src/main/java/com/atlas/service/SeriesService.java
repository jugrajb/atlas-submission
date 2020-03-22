package com.atlas.service;

import com.atlas.dao.SeriesDAO;
import com.atlas.model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {
    private final SeriesDAO seriesDAO;

    @Autowired
    public SeriesService(@Qualifier("postgres-series") SeriesDAO seriesDAO) {
        this.seriesDAO = seriesDAO;
    }

    public int add(Series series) {
        return seriesDAO.insert(series);
    }

    public List<Series> getAll() {
        return seriesDAO.getAll();
    }

    public Optional<Series> get(int id) {
        return seriesDAO.get(id);
    }

    public int delete(int id) {
        return seriesDAO.delete(id);
    }

    public int update(int id, Series series) {
        return seriesDAO.update(id, series);
    }
}
