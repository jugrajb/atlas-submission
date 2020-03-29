package com.atlas.dao;

import com.atlas.model.Condition;
import com.atlas.model.GameCard;
import com.atlas.model.Selection;

import java.util.List;
import java.util.Optional;

public interface GameCardDAO {
    Optional<GameCard> get(int id);

    List<GameCard> getAll();

    List<GameCard> getWithSelection(Selection selection);
}
