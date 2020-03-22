package com.atlas.service;

import com.atlas.dao.GameStudioDAO;
import com.atlas.model.GameStudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameStudioService {

    private GameStudioDAO gameStudioDAO;

    @Autowired
    public GameStudioService(@Qualifier("postgres-gamestudio") GameStudioDAO gameStudioDAO) {
        this.gameStudioDAO = gameStudioDAO;
    }

    public int add(GameStudio gameStudio) {
        return gameStudioDAO.insert(gameStudio);
    }

    public int update(int sid, GameStudio gameStudio) {
        return gameStudioDAO.update(sid, gameStudio);
    }

    public int delete(int sid) {
        return gameStudioDAO.delete(sid);
    }

    public Optional<GameStudio> get(int sid) {
        return gameStudioDAO.get(sid);
    }

    public List<GameStudio> getAll() {
        return gameStudioDAO.getAll();
    }
}
