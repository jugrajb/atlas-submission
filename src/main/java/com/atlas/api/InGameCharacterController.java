package com.atlas.api;

import com.atlas.model.InGameCharacter;
import com.atlas.service.InGameCharacterService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("app/in-game-character")
@RestController
public class InGameCharacterController {

    private InGameCharacterService inGameCharacterService;

    public InGameCharacterController(InGameCharacterService inGameCharacterService) {
        this.inGameCharacterService = inGameCharacterService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody InGameCharacter character) {
        inGameCharacterService.add(character);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") int id, @Valid @NonNull @RequestBody InGameCharacter character) {
        inGameCharacterService.update(id, character);
    }

    @DeleteMapping(path ="{id}")
    public void delete(@PathVariable("id") int id) {
        inGameCharacterService.delete(id);
    }

    @GetMapping(path ="{id}")
    public InGameCharacter get(@PathVariable("id") int id) {
        return inGameCharacterService.get(id).orElse(null);
    }

    @GetMapping
    public List<InGameCharacter> getAll() {
        return inGameCharacterService.getAll();
    }
}
