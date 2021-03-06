package com.atlas.api;

import com.atlas.model.GeneralUser;
import com.atlas.service.GeneralUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("app/general-user")
@RestController
public class GeneralUserController {

    private final GeneralUserService generalUserService;

    @Autowired
    public GeneralUserController(GeneralUserService generalUserService) {
        this.generalUserService = generalUserService;
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody GeneralUser generalUser) {
        generalUserService.add(generalUser);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        generalUserService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody GeneralUser generalUser, @Valid @NotNull @PathVariable int id) {
        generalUserService.update(id, generalUser);
    }

    @GetMapping(path = "{id}")
    public GeneralUser get(@PathVariable("id") int id) {
        return generalUserService.get(id).orElse(null);
    }

    @GetMapping
    public List<GeneralUser> getAll() {
        return generalUserService.getAll();
    }

    @PostMapping(
            path = "{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        generalUserService.uploadUserProfileImage(id, file);
    }

    @GetMapping(path = "{id}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("id") int id) {
        return generalUserService.downloadUserProfileImage(id);
    }
}
