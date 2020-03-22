package com.atlas.api;

import com.atlas.model.Director;
import com.atlas.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("app/director")
@RestController
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody Director director) {
        directorService.add(director);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody Director director, @Valid @NotNull @PathVariable int id) {
        directorService.update(id, director);
    }

    @GetMapping(path = "{id}")
    public Director get(@PathVariable("id") int id) {
        return directorService.get(id).orElse(null);
    }

    @GetMapping
    public List<Director> getAll() {
        return directorService.getAll();
    }
}
