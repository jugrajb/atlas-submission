package com.atlas.api;

import com.atlas.model.SummaryScore;
import com.atlas.service.SummaryScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("app/summary-score")
@RestController
public class SummaryScoreController {
    private SummaryScoreService summaryScoreService;

    @Autowired
    public SummaryScoreController(SummaryScoreService summaryScoreService) {
        this.summaryScoreService = summaryScoreService;
    }

    @GetMapping(path = "critic/{gid}")
    public SummaryScore getUserSummaryById(@PathVariable("gid") int gid) {
        return summaryScoreService.getUserSummaryById(gid).orElse(null);
    }

    @GetMapping(path = "user/{gid}")
    public SummaryScore getCriticSummaryById(@PathVariable("gid") int gid) {
        return summaryScoreService.getCriticSummaryById(gid).orElse(null);
    }

    @GetMapping(path = "user")
    public List<SummaryScore> getUserSummaryAll() {
        return summaryScoreService.getUserSummaryAll();
    }

    @GetMapping(path = "critic")
    public List<SummaryScore> getCriticSummaryAll() {
        return summaryScoreService.getCriticSummaryAll();
    }
}
