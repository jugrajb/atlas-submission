package com.atlas.api;

import com.atlas.model.GameAwardPair;
import com.atlas.service.GameAwardPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("app/game-award-pair")
@RestController
public class GameAwardPairController {
    private GameAwardPairService gameAwardPairService;

    @Autowired
    public GameAwardPairController(GameAwardPairService gameAwardPairService) {
        this.gameAwardPairService = gameAwardPairService;
    }

    @GetMapping(path = "{gid}")
    public List<GameAwardPair> get(@PathVariable("gid") int gid) {
        return gameAwardPairService.get(gid);
    }

    @GetMapping
    List<GameAwardPair> getAll() {
        return gameAwardPairService.getAll();
    }
}
