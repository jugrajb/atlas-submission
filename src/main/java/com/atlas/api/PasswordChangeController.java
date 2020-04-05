package com.atlas.api;

import com.atlas.model.PasswordChange;
import com.atlas.service.PasswordChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("app/password-change")
@RestController
public class PasswordChangeController {
    private PasswordChangeService passwordChangeService;

    @Autowired
    public PasswordChangeController(PasswordChangeService passwordChangeService) {
        this.passwordChangeService = passwordChangeService;
    }

    @GetMapping(path = "{uid}")
    public List<PasswordChange> getByUser(@PathVariable("uid") int uid) {
        return passwordChangeService.getByUser(uid);
    }

    @GetMapping
    public List<PasswordChange> getAll() {
        return passwordChangeService.getAll();
    }
}
