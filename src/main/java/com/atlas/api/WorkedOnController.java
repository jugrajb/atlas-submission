package com.atlas.api;

import com.atlas.model.WorkedOn;
import com.atlas.service.WorkedOnService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("app/worked-on")
@RestController
public class WorkedOnController {
    private WorkedOnService workedOnService;

    public WorkedOnController(WorkedOnService workedOnService) {
        this.workedOnService = workedOnService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody WorkedOn workedOn) {
        workedOnService.add(workedOn);
    }

    @PutMapping(path = "{gid}/{pid}")
    public void update(@PathVariable("gid") int gid,
                       @PathVariable("pid") int pid,
                       @Valid @NonNull @RequestBody WorkedOn workedOn
    ) {
        workedOnService.update(gid, pid, workedOn);
    }

    @DeleteMapping(path = "{gid}/{pid}")
    public void delete(@PathVariable("gid") int gid,
                       @PathVariable("pid") int pid) {
        workedOnService.delete(gid, pid);
    }

    @GetMapping(path = "{gid}/{pid}")
    public WorkedOn get(@PathVariable("gid") int gid,
                        @PathVariable("pid") int pid) {
        return workedOnService.get(gid, pid).orElse(null);
    }

    @GetMapping
    public List<WorkedOn> getAll() {
        return workedOnService.getAll();
    }


}
