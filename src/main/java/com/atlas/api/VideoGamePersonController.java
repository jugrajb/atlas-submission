package com.atlas.api;

import com.atlas.model.VideoGamePerson;
import com.atlas.service.VideoGamePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("app/video-game-person")
@RestController
public class VideoGamePersonController {
    private final VideoGamePersonService videoGamePersonService;

    @Autowired
    public VideoGamePersonController(VideoGamePersonService vgPersonService) {
        this.videoGamePersonService = vgPersonService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody VideoGamePerson person) {
        videoGamePersonService.add(person);
    }

    @PutMapping(path="{id}")
    public void update(@PathVariable("id") int id,
                       @Valid @NonNull @RequestBody VideoGamePerson person) {
        videoGamePersonService.update(id, person);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        videoGamePersonService.delete(id);
    }

    @GetMapping(path = "{id}")
    public VideoGamePerson get(@PathVariable("id") int id) {
        return videoGamePersonService.get(id).orElse(null);
    }

    @GetMapping
    public List<VideoGamePerson> getAll() {
        return videoGamePersonService.getAll();
    }
}
