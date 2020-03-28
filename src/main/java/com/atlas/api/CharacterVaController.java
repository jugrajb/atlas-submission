package com.atlas.api;

import com.atlas.model.CharacterVa;
import com.atlas.service.CharacterVaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
