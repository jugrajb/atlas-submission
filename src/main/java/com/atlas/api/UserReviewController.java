package com.atlas.api;

import com.atlas.model.UserReview;
import com.atlas.service.UserReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RequestMapping("app/user-review")
@RestController
public class UserReviewController {

    private final UserReviewService userReviewService;

    @Autowired
    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody UserReview userReview) {
        userReviewService.add(userReview);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        userReviewService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody UserReview userReview, @Valid @NotNull @PathVariable int id) {
        userReviewService.update(id, userReview);
    }

    @GetMapping(path = "{id}")
    public UserReview get(@PathVariable("id") int id) {
        return userReviewService.get(id).orElse(null);
    }

    @GetMapping
    public List<UserReview> getAll() {
        return userReviewService.getAll();
    }

    @GetMapping(path = "game/{gid}")
    public List<Map<String, Object>> getAllByGid(@PathVariable("gid") int id) {
      return userReviewService.getAllByGid(id);
    }
}
