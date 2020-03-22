package com.atlas.api;

import com.atlas.model.GamePlatform;
import com.atlas.service.GamePlatformService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("app/gameplatform")
@RestController
public class GamePlatformController {

    private GamePlatformService gamePlatformService;

    public GamePlatformController(GamePlatformService gamePlatformService) {
        this.gamePlatformService = gamePlatformService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody GamePlatform gamePlatform) {
        gamePlatformService.add(gamePlatform);
    }

    @PutMapping(path = "{gpid}")
    public void update(@PathVariable("gpid") int gpid,
                       @Valid @NonNull @RequestBody GamePlatform gamePlatform) {
        gamePlatformService.update(gpid, gamePlatform);
    }

    @DeleteMapping(path = "{gpid}")
    public void delete(@PathVariable("gpid") int gpid) {
        gamePlatformService.delete(gpid);
    }

    @GetMapping(path = "{gpid}")
    public GamePlatform get(@PathVariable("gpid") int gpid) {
        return gamePlatformService.get(gpid).orElse(null);
    }

    @GetMapping
    public List<GamePlatform> getAll() {
        return gamePlatformService.getAll();
    }
}
