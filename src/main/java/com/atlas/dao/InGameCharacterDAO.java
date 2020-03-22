package com.atlas.dao;

import com.atlas.model.InGameCharacter;

import java.util.List;
import java.util.Optional;

public interface InGameCharacterDAO {

    int insert(InGameCharacter character);

    int update(int id, InGameCharacter character);

    int delete(int id);

    Optional<InGameCharacter> get(int id);

    List<InGameCharacter> getAll();
}
