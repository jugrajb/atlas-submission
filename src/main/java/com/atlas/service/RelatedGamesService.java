package com.atlas.service;

import com.atlas.dao.RelatedGamesDAO;
import com.atlas.model.GameCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatedGamesService {
    private RelatedGamesDAO relatedGamesDAO;

    @Autowired
    public RelatedGamesService(@Qualifier("postgres-relatedgames") RelatedGamesDAO relatedGamesDAO) {
        this.relatedGamesDAO = relatedGamesDAO;
    }

    public List<GameCard> get(int gid) {
        return relatedGamesDAO.get(gid);
    }
}
