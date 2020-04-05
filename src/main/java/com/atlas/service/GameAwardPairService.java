package com.atlas.service;

import com.atlas.dao.GameAwardPairDAO;
import com.atlas.model.GameAwardPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameAwardPairService {
    private GameAwardPairDAO gameAwardPairDAO;

    @Autowired
    public GameAwardPairService(@Qualifier("postgres-gameawardpair") GameAwardPairDAO gameAwardPairDAO) {
        this.gameAwardPairDAO = gameAwardPairDAO;
    }

    public List<GameAwardPair> get(int gid) {
        return gameAwardPairDAO.get(gid);
    }

    public List<GameAwardPair> getAll() {
        return gameAwardPairDAO.getAll();
    }

}
