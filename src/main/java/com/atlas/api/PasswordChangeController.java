package com.atlas.api;

import com.atlas.model.PasswordChange;
import com.atlas.service.PasswordChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
