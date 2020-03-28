package com.atlas.api;

import com.atlas.model.Condition;
import com.atlas.model.PersonCard;
import com.atlas.service.PersonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("app/person-card")
@RestController
public class PersonCardController {
    private PersonCardService personCardService;

    @Autowired
    public PersonCardController(PersonCardService personCardService) {
        this.personCardService = personCardService;
    }

    @GetMapping(path = "{pid}")
    public PersonCard get(@PathVariable("pid") int pid) {
        return personCardService.get(pid).orElse(null);
    }

    @GetMapping
    public List<PersonCard> getAll() {
        return personCardService.getAll();
    }

    @PostMapping
    public List<PersonCard> getWithCondition(@Valid @NonNull @RequestBody Condition condition) {
        return personCardService.getWithCondition(condition);
    }
}
