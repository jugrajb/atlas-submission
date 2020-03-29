package com.atlas.dao;

import com.atlas.model.Condition;
import com.atlas.model.GameCard;

import java.util.List;
import java.util.Optional;

public interface GameCardDAO {
    Optional<GameCard> get(int id);

    List<GameCard> getAll();

    List<GameCard> getWithCondition(Condition condition);
}
