package com.atlas.api;

import com.atlas.model.PublishingCompany;
import com.atlas.service.PublishingCompanyService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("app/publishing-company")
@RestController
public class PublishingCompanyController {

    private PublishingCompanyService publishingCompanyService;

    public PublishingCompanyController(PublishingCompanyService publishingCompanyService) {
        this.publishingCompanyService = publishingCompanyService;
    }

    @PostMapping
    public void add(@Valid @NonNull @RequestBody PublishingCompany publishingCompany) {
        publishingCompanyService.add(publishingCompany);
    }

    @PutMapping(path = "{pcid}")
    public void update(@PathVariable("pcid") int pcid,
                       @Valid @NonNull @RequestBody PublishingCompany publishingCompany) {
        publishingCompanyService.update(pcid, publishingCompany);
    }

    @DeleteMapping(path = "{pcid}")
    public void delete(@PathVariable("pcid") int pcid) {
        publishingCompanyService.delete(pcid);
    }

    @GetMapping(path = "{pcid}")
    public PublishingCompany get(@PathVariable("pcid") int pcid) {
        return publishingCompanyService.get(pcid).orElse(null);
    }

    @GetMapping
    public List<PublishingCompany> getAll() {
        return publishingCompanyService.getAll();
    }
}
