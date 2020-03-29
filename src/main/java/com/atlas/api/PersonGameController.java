package com.atlas.api;

import com.atlas.model.GameByPerson;
import com.atlas.model.PersonByGame;
import com.atlas.service.PersonGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("app/person-game")
@RestController
public class PersonGameController {
    private PersonGameService personGameService;

    @Autowired
    public PersonGameController(PersonGameService personGameService) {
        this.personGameService = personGameService;
    }

    @GetMapping(path = "person/{id}")
    public List<GameByPerson> getGamesByPerson(@PathVariable("id") int id) {
        return personGameService.getGamesByPerson(id);
    }

    @GetMapping(path = "game/{id}")
    public List<PersonByGame> getPeopleByGame(@PathVariable("id")int gid) {
        return personGameService.getPeopleByGame(gid);
    }
}
