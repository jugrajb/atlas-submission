package com.atlas.dao;

import com.atlas.model.GamePhotos;

import java.util.List;
import java.util.Optional;

public interface GamePhotosDAO {
    int insert(GamePhotos gamePhotos);

    int update(int id, GamePhotos gamePhotos);

    int delete(int id);

    Optional<GamePhotos> get(int id);

    List<GamePhotos> getAll();
}
