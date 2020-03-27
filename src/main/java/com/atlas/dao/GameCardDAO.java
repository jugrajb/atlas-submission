package com.atlas.dao;

import com.atlas.model.GameCard;

import java.util.List;
import java.util.Optional;

public interface GameCardDAO {
    Optional<GameCard> get(int id);

    List<GameCard> getAll();

    // TODO: getAllWithConditions
    // List<GameCard> getAll(StringCondition[] strConditions, IntCondition[] intConditions);
}
