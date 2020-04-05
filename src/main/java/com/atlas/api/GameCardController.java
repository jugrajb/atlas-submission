package com.atlas.api;


import com.atlas.model.GameCard;
import com.atlas.model.Selection;
import com.atlas.service.GameCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
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

    @PostMapping
    public List<GameCard> getAllWithSelection(@Valid @NonNull @RequestBody Selection selection) {
        return gameCardService.getWithSelection(selection);
    }
}
