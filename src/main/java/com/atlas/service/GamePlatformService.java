package com.atlas.service;

import com.atlas.dao.GamePlatformDAO;
import com.atlas.model.GamePlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamePlatformService {

    private GamePlatformDAO gamePlatformDAO;

    @Autowired
    public GamePlatformService(@Qualifier("postgres-gameplatform") GamePlatformDAO gamePlatformDAO) {
        this.gamePlatformDAO = gamePlatformDAO;
    }

    public int add(GamePlatform gamePlatform) {
        return gamePlatformDAO.insert(gamePlatform);
    }

    public int update(int gpid, GamePlatform gamePlatform) {
        return gamePlatformDAO.update(gpid, gamePlatform);
    }

    public int delete(int gpid) {
        return gamePlatformDAO.delete(gpid);
    }

    public Optional<GamePlatform> get(int gpid) {
        return gamePlatformDAO.get(gpid);
    }

    public List<GamePlatform> getAll() {
        return gamePlatformDAO.getAll();
    }
}
