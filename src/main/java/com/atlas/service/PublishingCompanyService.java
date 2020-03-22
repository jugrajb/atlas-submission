package com.atlas.service;

import com.atlas.dao.PublishingCompanyDAO;
import com.atlas.model.PublishingCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublishingCompanyService {
    private PublishingCompanyDAO publishingCompanyDAO;

    @Autowired
    public PublishingCompanyService(@Qualifier("postgres-publishingcompany")
                                                PublishingCompanyDAO publishingCompanyDAO) {
        this.publishingCompanyDAO = publishingCompanyDAO;
    }

    public int add(PublishingCompany publishingCompany) {
        return publishingCompanyDAO.insert(publishingCompany);
    }

    public int update(int pcid, PublishingCompany publishingCompany) {
        return publishingCompanyDAO.update(pcid, publishingCompany);
    }

    public int delete(int pcid) {
        return publishingCompanyDAO.delete(pcid);
    }

    public Optional<PublishingCompany> get(int pcid) {
        return publishingCompanyDAO.get(pcid);
    }

    public List<PublishingCompany> getAll() {
        return publishingCompanyDAO.getAll();
    }
}
