package com.atlas.api;

import com.atlas.model.VideoGamePerson;
import com.atlas.service.VideoGamePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("app/videogameperson")
@RestController
public class VideoGamePersonController {
    private final VideoGamePersonService vgPersonService;

    @Autowired
    public VideoGamePersonController(VideoGamePersonService vgPersonService) {
        this.vgPersonService = vgPersonService;
    }

    @PostMapping
    public void addVideoGamePerson(@Valid @NonNull @RequestBody VideoGamePerson person) {
        vgPersonService.addVideoGame(person);
    }

    @GetMapping
    public List<VideoGamePerson> getAllVideoGamePeople() {
        return vgPersonService.getAllVideoGamePeople();
    }

    @GetMapping(path = "{id}")
    public VideoGamePerson getVideoGamePersonById(@PathVariable("id") int id) {
        return vgPersonService.getVideoGamePersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteVideoGamePerson(@PathVariable("id") int id) {
        vgPersonService.deleteVideoGamePerson(id);
    }

    @PutMapping(path="{id}")
    public void updateVideoGamePerson(@PathVariable("id") int id,
                                     @Valid @NonNull @RequestBody VideoGamePerson person) {
        vgPersonService.updateVideoGamePerson(id, person);
    }


}