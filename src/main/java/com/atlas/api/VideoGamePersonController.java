package com.atlas.api;

import com.atlas.model.VideoGamePerson;
import com.atlas.service.VideoGamePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("app/videogameperson")
@RestController
public class VideoGamePersonController {
    private final VideoGamePersonService vgPersonService;

    @Autowired
    public VideoGamePersonController(VideoGamePersonService vgPersonService) {
        this.vgPersonService = vgPersonService;
    }

    @GetMapping
    public List<VideoGamePerson> getAllVideoGamePeople() {
        return vgPersonService.getAllVideoGamePeople();
    }
}
