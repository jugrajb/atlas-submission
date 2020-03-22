package com.atlas.api;

import com.atlas.model.AppearsIn;
import com.atlas.model.InGameCharacter;
import com.atlas.service.AppearsInService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("app/appearsin")
@RestController
public class AppearsInController {

    private AppearsInService appearsInService;

    public AppearsInController(AppearsInService appearsInService) {
        this.appearsInService = appearsInService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody AppearsIn appearsIn) {
        appearsInService.add(appearsIn);
    }

    @PutMapping(path = "{gid}/{cid}")
    public void update(@PathVariable("gid") int gid,
                       @PathVariable("cid") int cid,
                       @Valid @NonNull @RequestBody AppearsIn appearsIn) {
        appearsInService.update(gid, cid, appearsIn);
    }

    @DeleteMapping(path = "{gid}/{cid}")
    public void delete(@PathVariable("gid") int gid,
                       @PathVariable("cid") int cid) {
        appearsInService.delete(gid, cid);
    }

    @GetMapping(path = "{gid}/{cid}")
    public AppearsIn get(@PathVariable("gid") int gid,
                               @PathVariable("cid") int cid) {
        return appearsInService.get(gid, cid).orElse(null);
    }

    @GetMapping
    public List<AppearsIn> getAll() {
        return appearsInService.getAll();
    }
}
