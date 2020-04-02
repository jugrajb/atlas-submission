package com.atlas.api;

import com.atlas.model.PersonCard;
import com.atlas.model.Selection;
import com.atlas.service.PersonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
    public List<PersonCard> getWithSelection(@Valid @NonNull @RequestBody Selection selection) {
        return personCardService.getWithSelection(selection);
    }
}
