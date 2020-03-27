package com.atlas.api;


import com.atlas.model.GameCard;
import com.atlas.service.GameCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("app/game-card")
@RestController
public class GameCardController {
    private GameCardService gameCardService;

    @Autowired
    public GameCardController(GameCardService gameCardService) {
        this.gameCardService = gameCardService;
    }

    @GetMapping(path = "{id}")
    public GameCard get(@PathVariable("id") int id) {
        return gameCardService.get(id).orElse(null);
    }

    @GetMapping
    public List<GameCard> getAll() {
        return gameCardService.getAll();
    }

    // TODO: Have endpoint where you can add a selection condition in body
}
