package com.atlas.api;

import com.atlas.dao.AwardOrganizationDAO;
import com.atlas.model.AwardOrganization;
import com.atlas.service.AwardOrganizationService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("app/award-organization")
@RestController
public class AwardOrganizationController {

    private AwardOrganizationService awardOrganizationService;

    public AwardOrganizationController(AwardOrganizationService awardOrganizationService) {
        this.awardOrganizationService = awardOrganizationService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody AwardOrganization awardOrganization) {
        awardOrganizationService.add(awardOrganization);
    }

    @PutMapping(path = "{oid}")
    public void update(@PathVariable("oid") int oid,
                       @Valid @NonNull @RequestBody AwardOrganization awardOrganization
    ) {
        awardOrganizationService.update(oid, awardOrganization);
    }

    @DeleteMapping(path = "{oid}")
    public void update(@PathVariable("oid") int oid) {
        awardOrganizationService.delete(oid);
    }

    @GetMapping(path = "{oid}")
    public AwardOrganization get(@PathVariable("oid") int oid) {
        return awardOrganizationService.get(oid).orElse(null);
    }

    @GetMapping
    public List<AwardOrganization> getAll() {
        return awardOrganizationService.getAll();
    }
}
