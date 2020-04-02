package com.atlas.api;

import com.atlas.model.CharacterVa;
import com.atlas.service.CharacterVaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("app/character-va")
@RestController
public class CharacterVaController {
    private CharacterVaService characterVaService;

    @Autowired
    public CharacterVaController(CharacterVaService characterVaService) {
        this.characterVaService = characterVaService;
    }

    @GetMapping(path = "{gid}")
    public List<CharacterVa> getById(@PathVariable("gid") int gid) {
        return characterVaService.getById(gid);
    }

    @GetMapping
    public List<CharacterVa> getAll() {
        return characterVaService.getAll();
    }
}
