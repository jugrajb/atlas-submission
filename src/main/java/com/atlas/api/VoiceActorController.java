package com.atlas.api;

import com.atlas.model.VoiceActor;
import com.atlas.service.VoiceActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("app/voice-actor")
@RestController
public class VoiceActorController {

    private final VoiceActorService voiceActorService;

    @Autowired
    public VoiceActorController(VoiceActorService voiceActorService) {
        this.voiceActorService = voiceActorService;
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody VoiceActor voiceActor) {
        voiceActorService.add(voiceActor);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        voiceActorService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody VoiceActor voiceActor, @Valid @NotNull @PathVariable int id) {
        voiceActorService.update(id, voiceActor);
    }

    @GetMapping(path = "{id}")
    public VoiceActor get(@PathVariable("id") int id) {
        return voiceActorService.get(id).orElse(null);
    }

    @GetMapping
    public List<VoiceActor> getAll() {
        return voiceActorService.getAll();
    }
}
