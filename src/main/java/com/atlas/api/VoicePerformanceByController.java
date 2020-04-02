package com.atlas.api;

import com.atlas.model.VoicePerformanceBy;
import com.atlas.service.VoicePerformanceByService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("app/voice-performance-by")
@RestController
public class VoicePerformanceByController {

    private VoicePerformanceByService voicePerformanceByService;

    public VoicePerformanceByController(VoicePerformanceByService voicePerformanceByService) {
        this.voicePerformanceByService = voicePerformanceByService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody VoicePerformanceBy voicePerformanceBy) {
        voicePerformanceByService.add(voicePerformanceBy);
    }

    @PutMapping(path = "{cid}/{pid}")
    public void update(@PathVariable("cid") int cid,
                       @PathVariable("pid") int pid,
                       @Valid @NonNull @RequestBody VoicePerformanceBy voicePerformanceBy
    ) {
        voicePerformanceByService.update(cid, pid, voicePerformanceBy);
    }

    @DeleteMapping(path = "{cid}/{pid}")
    public void delete(@PathVariable("cid") int cid,
                       @PathVariable("pid") int pid) {
        voicePerformanceByService.delete(cid, pid);
    }

    @GetMapping(path = "{cid}/{pid}")
    public VoicePerformanceBy get(@PathVariable("cid") int cid,
                    @PathVariable("pid") int pid) {
        return voicePerformanceByService.get(cid, pid).orElse(null);
    }

    @GetMapping
    public List<VoicePerformanceBy> getAll() {
        return voicePerformanceByService.getAll();
    }
}
