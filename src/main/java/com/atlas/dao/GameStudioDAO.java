package com.atlas.dao;

import com.atlas.model.GameStudio;

import java.util.List;
import java.util.Optional;

public interface GameStudioDAO {
    int insert(GameStudio gameStudio);

    int update(int sid, GameStudio gameStudio);

    int delete(int sid);

    Optional<GameStudio> get(int sid);

    List<GameStudio> getAll();
}
