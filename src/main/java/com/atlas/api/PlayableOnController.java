package com.atlas.api;

import com.atlas.model.PlayableOn;
import com.atlas.service.PlayableOnService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("app/playable-on")
@RestController
public class PlayableOnController {

    private PlayableOnService playableOnService;

    public PlayableOnController(PlayableOnService playableOnService) {
        this.playableOnService = playableOnService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody PlayableOn playableOn) {
        playableOnService.add(playableOn);
    }

    @PutMapping(path = "{gid}/{gpid}")
    public void update(@PathVariable("gid") int gid,
                       @PathVariable("gpid") int gpid,
                       @Valid @NonNull @RequestBody PlayableOn playableOn) {
        playableOnService.update(gid, gpid, playableOn);
    }

    @DeleteMapping(path = "{gid}/{gpid}")
    public void delete(@PathVariable("gid") int gid,
                       @PathVariable("gpid") int gpid) {
        playableOnService.delete(gid, gpid);
    }

    @GetMapping(path = "{gid}/{gpid}")
    public PlayableOn get(@PathVariable("gid") int gid,
                          @PathVariable("gpid") int gpid) {
        return playableOnService.get(gid, gpid).orElse(null);
    }

    @GetMapping
    public List<PlayableOn> getAll() {
        return playableOnService.getAll();
    }


}
