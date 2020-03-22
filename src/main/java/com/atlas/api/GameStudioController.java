package com.atlas.api;

import com.atlas.model.GameStudio;
import com.atlas.service.GameStudioService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("app/gamestudio")
@RestController
public class GameStudioController {

    private GameStudioService gameStudioService;

    public GameStudioController(GameStudioService gameStudioService) {
        this.gameStudioService = gameStudioService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody GameStudio gameStudio) {
        gameStudioService.add(gameStudio);
    }

    @PutMapping(path = "{sid}")
    public void update(@PathVariable("sid") int sid,
                       @Valid @NonNull @RequestBody GameStudio gameStudio) {
        gameStudioService.update(sid, gameStudio);
    }

    @DeleteMapping(path = "{sid}")
    public void delete(@PathVariable("sid") int sid) {
        gameStudioService.delete(sid);
    }

    @GetMapping(path = "{sid}")
    public GameStudio get(@PathVariable("sid") int sid) {
        return gameStudioService.get(sid).orElse(null);
    }

    @GetMapping
    public List<GameStudio> getAll() {
        return gameStudioService.getAll();
    }
}
