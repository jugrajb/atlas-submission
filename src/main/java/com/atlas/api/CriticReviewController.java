package com.atlas.api;

import com.atlas.model.CriticReview;
import com.atlas.service.CriticReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("app/critic-review")
@RestController
public class CriticReviewController {

    private final CriticReviewService criticReviewService;

    @Autowired
    public CriticReviewController(CriticReviewService criticReviewService) {
        this.criticReviewService = criticReviewService;
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody CriticReview criticReview) {
        criticReviewService.add(criticReview);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        criticReviewService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody CriticReview criticReview, @Valid @NotNull @PathVariable int id) {
        criticReviewService.update(id, criticReview);
    }

    @GetMapping(path = "{id}")
    public CriticReview get(@PathVariable("id") int id) {
        return criticReviewService.get(id).orElse(null);
    }

    @GetMapping
    public List<CriticReview> getAll() {
        return criticReviewService.getAll();
    }

    @GetMapping(path = "game/{gid}")
    public List<CriticReview> getAllByGid(@PathVariable("gid") int id) {
      return criticReviewService.getAllByGid(id);
    }
}
