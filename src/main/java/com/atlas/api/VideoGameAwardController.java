package com.atlas.api;

import com.atlas.model.VideoGameAward;
import com.atlas.service.VideoGameAwardService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("app/video-game-award")
@RestController
public class VideoGameAwardController {

    private VideoGameAwardService videoGameAwardService;

    public VideoGameAwardController(VideoGameAwardService videoGameAwardService) {
        this.videoGameAwardService = videoGameAwardService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody VideoGameAward videoGameAward) {
        videoGameAwardService.add(videoGameAward);
    }

    @PutMapping(path = "{name}/{oid}")
    public void update(@PathVariable("name") String name,
                       @PathVariable("oid") int oid,
                       @Valid @NonNull @RequestBody VideoGameAward videoGameAward
    ) {
        videoGameAwardService.update(name, oid, videoGameAward);
    }

    @DeleteMapping(path = "{name}/{oid}")
    public void delete(@PathVariable("name") String name,
                       @PathVariable("oid") int oid) {
        videoGameAwardService.delete(name, oid);
    }

    @GetMapping(path = "{name}/{oid}")
    public VideoGameAward get(@PathVariable("name") String name,
                    @PathVariable("oid") int oid) {
        return videoGameAwardService.get(name, oid).orElse(null);
    }

    @GetMapping
    public List<VideoGameAward> getAll() {
        return videoGameAwardService.getAll();
    }

}
