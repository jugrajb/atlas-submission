package com.atlas.api;

import com.atlas.model.Administrator;
import com.atlas.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("app/admin")
@RestController
public class AdministratorController {

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody Administrator administrator) {
        administratorService.add(administrator);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        administratorService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody Administrator administrator, @Valid @NotNull @PathVariable int id) {
        administratorService.update(id, administrator);
    }

    @GetMapping(path = "{id}")
    public Administrator get(@PathVariable("id") int id) {
        return administratorService.get(id).orElse(null);
    }

    @GetMapping
    public List<Administrator> getAll() {
        return administratorService.getAll();
    }
}
