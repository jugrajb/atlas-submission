package com.atlas.api;

import com.atlas.service.NaturalJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RequestMapping("app/natural-join")
@RestController
public class NaturalJoinController {
    private NaturalJoinService naturalJoinService;

    @Autowired
    public NaturalJoinController(NaturalJoinService naturalJoinService) {
        this.naturalJoinService = naturalJoinService;
    }

    @GetMapping(path = "{relation1}/{relation2}")
    public List<Map<String, Object>> get(@PathVariable("relation1") String relation1,
                                         @PathVariable("relation2") String relation2) {
        return naturalJoinService.get(relation1, relation2);
    }
}
