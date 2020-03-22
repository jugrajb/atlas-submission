package com.atlas.dao;

import com.atlas.model.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorDAO {
    int insert(Director director);

    int update(int id, Director director);

    int delete(int id);

    Optional<Director> get(int id);

    List<Director> getAll();
}
