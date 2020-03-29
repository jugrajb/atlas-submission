package com.atlas.dao;

import com.atlas.model.GameCard;

import java.util.List;

public interface RelatedGamesDAO {
    List<GameCard> get(int gid);
}
