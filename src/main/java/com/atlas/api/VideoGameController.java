package com.atlas.api;

import com.atlas.model.VideoGame;
import com.atlas.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("app/video-game")
@RestController
public class VideoGameController {

  private final VideoGameService videoGameService;

  @Autowired
  public VideoGameController(VideoGameService videoGameService) {
    this.videoGameService = videoGameService;
  }

  @PostMapping
  public void add(@Valid @NotNull @RequestBody VideoGame videoGame) {
    videoGameService.add(videoGame);
  }

  @DeleteMapping(path = "{id}")
  public void delete(@PathVariable("id") int id) {
    videoGameService.delete(id);
  }

  @PutMapping(path = "{id}")
  public void update(@RequestBody VideoGame videoGame, @Valid @NotNull @PathVariable int id) {
    videoGameService.update(id, videoGame);
  }

  @GetMapping(path = "{id}")
  public VideoGame get(@PathVariable("id") int id) {
    return videoGameService.get(id).orElse(null);
  }

  @GetMapping
  public List<VideoGame> getAll() {
    return videoGameService.getAll();
  }
}
