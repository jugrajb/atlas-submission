package com.atlas.api;

import com.atlas.service.UserFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("app/user-filter")
@RestController
public class UserFilterController {
    private UserFilterService userFilterService;

    @Autowired
    public UserFilterController(UserFilterService userFilterService) {
        this.userFilterService = userFilterService;
    }

    @PostMapping(path = "{id}")
    public Map<String, Object> get(@PathVariable("id") int id,
                                   @RequestBody Map<String, List<String>> body) {
        List<String> columns = body.get("columns");
        return userFilterService.get(id, columns).orElse(null);
    }

    @PostMapping
    public List<Map<String, Object>> getAll(@RequestBody Map<String, List<String>> body) {
        List<String> columns = body.get("columns");
        return userFilterService.getAll(columns);
    }
}
