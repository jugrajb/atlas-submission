package com.atlas.dao;

import com.atlas.model.GameAwardPair;

import java.util.List;

public interface GameAwardPairDAO {
    List<GameAwardPair> get(int gid);

    List<GameAwardPair> getAll();
}
