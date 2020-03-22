package com.atlas.dao;

import com.atlas.model.GamePlatform;

import java.util.List;
import java.util.Optional;

public interface GamePlatformDAO {
    int insert(GamePlatform gamePlatform);

    int update(int gpid, GamePlatform gamePlatform);

    int delete(int gpid);

    Optional<GamePlatform> get(int gpid);

    List<GamePlatform> getAll();
}
