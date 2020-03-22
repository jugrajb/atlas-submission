package com.atlas.api;

import com.atlas.model.Users;
import com.atlas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("app/users")
@RestController
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody Users user) {
        usersService.add(user);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") int id) {
        usersService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@RequestBody Users users, @Valid @NotNull @PathVariable int id) {
        usersService.update(id, users);
    }

    @GetMapping(path = "{id}")
    public Users get(@PathVariable("id") int id) {
        return usersService.get(id).orElse(null);
    }

    @GetMapping
    public List<Users> getAll() {
        return usersService.getAll();
    }
}
