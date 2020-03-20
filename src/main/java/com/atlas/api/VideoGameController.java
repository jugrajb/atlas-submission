package com.atlas.api;

import com.atlas.model.VideoGame;
import com.atlas.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("app/videogame")
@RestController
public class VideoGameController {

  private final VideoGameService videoGameService;

  @Autowired
  public VideoGameController(VideoGameService videoGameService) {
    this.videoGameService = videoGameService;
  }

  @PostMapping
  public void addVideoGame(@Valid @NotNull @RequestBody VideoGame videoGame) {
    videoGameService.addVideoGame(videoGame);
  }

  @DeleteMapping(path = "{id}")
  public void deleteVideoGame(@PathVariable("id") int id) {
    videoGameService.deleteVideoGame(id);
  }

  @PutMapping(path = "{id}")
  public void updateVideoGame(@RequestBody VideoGame videoGame, @Valid @NotNull @PathVariable int id) {
    videoGameService.updateVideoGame(id, videoGame);
  }

  @GetMapping(path = "{id}")
  public VideoGame getVideoGame(@PathVariable("id") int id) {
    return videoGameService.getVideoGameById(id).orElse(null);
  }

  @GetMapping
  public List<VideoGame> getAllVideoGame() {
    return videoGameService.getAllVideoGame();
  }
}
