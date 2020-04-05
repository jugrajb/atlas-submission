package com.atlas.api;

import com.atlas.model.Series;
import com.atlas.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("app/series")
@RestController
public class SeriesController {

    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody Series series) {
        seriesService.add(series);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        seriesService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody Series series, @Valid @NotNull @PathVariable int id) {
        seriesService.update(id, series);
    }

    @GetMapping(path = "{id}")
    public Series get(@PathVariable("id") int id) {
        return seriesService.get(id).orElse(null);
    }

    @GetMapping
    public List<Series> getAll() {
        return seriesService.getAll();
    }
}
