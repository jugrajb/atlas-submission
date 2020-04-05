package com.atlas.api;

import com.atlas.model.GamePhotos;
import com.atlas.service.GamePhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("app/game-photos")
@RestController
public class GamePhotosController {

    private final GamePhotosService gamePhotosService;

    @Autowired
    public GamePhotosController(GamePhotosService gamePhotosService) {
        this.gamePhotosService = gamePhotosService;
    }

    @PostMapping(
            path = "{id}/image/upload/cover",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadCover(@PathVariable("id") int id,
                            @RequestParam("file") MultipartFile file
    ) {
        gamePhotosService.uploadCover(id, file);
    }

    @PostMapping(
            path = "{id}/image/upload/banner",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("id") int id,
                                       @RequestParam("file") MultipartFile file
    ) {
        gamePhotosService.uploadBanner(id, file);
    }

    @GetMapping(path = "{id}/cover/download")
    public byte[] downloadCoverImage(@PathVariable("id") int id) {
        return gamePhotosService.downloadCoverImage(id);
    }

    @GetMapping(path = "{id}/banner/download")
    public byte[] downloadBannerImage(@PathVariable("id") int id) {
        return gamePhotosService.downloadBannerImage(id);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        gamePhotosService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody GamePhotos gamePhotos , @Valid @NotNull @PathVariable int id) {
        gamePhotosService.update(id, gamePhotos);
    }

    @GetMapping(path = "{id}")
    public GamePhotos get(@PathVariable("id") int id) {
        return gamePhotosService.get(id).orElse(null);
    }

    @GetMapping
    public List<GamePhotos> getAll() {
        return gamePhotosService.getAll();
    }
}
