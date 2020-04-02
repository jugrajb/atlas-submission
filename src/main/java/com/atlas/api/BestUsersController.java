package com.atlas.api;

import com.atlas.model.GeneralUser;
import com.atlas.service.BestUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("app/best-users")
@RestController
public class BestUsersController {
    private BestUsersService bestUsersService;

    @Autowired
    public BestUsersController(BestUsersService bestUsersService) {
        this.bestUsersService = bestUsersService;
    }

    @GetMapping
    public List<GeneralUser> getAll() {
        return bestUsersService.getAll();
    }
}
