package com.atlas.service;

import com.atlas.dao.InGameCharacterDAO;
import com.atlas.model.InGameCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InGameCharacterService {
    private final InGameCharacterDAO inGameCharacterDAO;

    @Autowired
    public InGameCharacterService(@Qualifier("postgres-ingamecharacter") InGameCharacterDAO inGameCharacterDAO) {
        this.inGameCharacterDAO = inGameCharacterDAO;
    }

    public int add(InGameCharacter character) {
        return inGameCharacterDAO.insert(character);
    }

    public int update(int id, InGameCharacter character) {
        return inGameCharacterDAO.update(id, character);
    }

    public int delete(int id) {
        return inGameCharacterDAO.delete(id);
    }

    public Optional<InGameCharacter> get(int id) {
        return inGameCharacterDAO.get(id);
    }

    public List<InGameCharacter> getAll() {
        return inGameCharacterDAO.getAll();
    }
}
