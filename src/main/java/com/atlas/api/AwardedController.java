package com.atlas.api;

import com.atlas.model.Awarded;
import com.atlas.service.AwardedService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("app/awarded")
@RestController
public class AwardedController {

    private AwardedService awardedService;

    public AwardedController(AwardedService awardedService) {
        this.awardedService = awardedService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody Awarded awarded) {
        awardedService.add(awarded);
    }

    @PutMapping(path = "{name}/{oid}/{gid}")
    public void update(@PathVariable("name") String name,
                       @PathVariable("oid") int oid,
                       @PathVariable("gid") int gid,
                       @Valid @NonNull @RequestBody Awarded awarded
    ) {
        awardedService.update(name, oid, gid, awarded);
    }

    @DeleteMapping(path = "{name}/{oid}/{gid}")
    public void delete(@PathVariable("name") String name,
                       @PathVariable("oid") int oid,
                       @PathVariable("gid") int gid
    ) {
        awardedService.delete(name, oid, gid);
    }

    @GetMapping(path = "{name}/{oid}/{gid}")
    public Awarded get(@PathVariable("name") String name,
                    @PathVariable("oid") int oid,
                    @PathVariable("gid") int gid
    ) {
        return awardedService.get(name, oid, gid).orElse(null);
    }

    @GetMapping
    public List<Awarded> getAll() {
        return awardedService.getAll();
    }

}
