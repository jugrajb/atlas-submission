package com.atlas.api;

import com.atlas.model.GameCard;
import com.atlas.service.RelatedGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
