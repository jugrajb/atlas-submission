package com.atlas.api;

import com.atlas.model.GameCard;
import com.atlas.service.RelatedGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("app/related-games")
@RestController
public class RelatedGamesController {
    private RelatedGamesService relatedGamesService;

    @Autowired
    public RelatedGamesController(RelatedGamesService relatedGamesService) {
        this.relatedGamesService = relatedGamesService;
    }

    @GetMapping(path = "{id}")
    public List<GameCard> get(@PathVariable int id) {
        return relatedGamesService.get(id);
    }
}
