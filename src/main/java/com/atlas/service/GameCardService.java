package com.atlas.service;

import com.atlas.dao.GameCardDAO;
import com.atlas.model.GameCard;
import com.atlas.model.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameCardService {
    private GameCardDAO gameCardDAO;

    @Autowired
    public GameCardService(@Qualifier("postgres-gamecard") GameCardDAO gameCardDAO) {
        this.gameCardDAO = gameCardDAO;
    }

    public Optional<GameCard> get(int id) {
        return gameCardDAO.get(id);
    }

    public List<GameCard> getAll() {
        return gameCardDAO.getAll();
    }

    public List<GameCard> getWithSelection(Selection selection) {
        return gameCardDAO.getWithSelection(selection);
    }
}
