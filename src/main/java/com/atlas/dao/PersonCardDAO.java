package com.atlas.dao;

import com.atlas.model.Condition;
import com.atlas.model.PersonCard;

import java.util.List;
import java.util.Optional;

public interface PersonCardDAO {
    Optional<PersonCard> get(int id);

    List<PersonCard> getAll();

    List<PersonCard> getWithCondition(Condition condition);
}
