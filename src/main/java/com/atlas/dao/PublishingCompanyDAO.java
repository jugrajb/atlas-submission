package com.atlas.dao;

import com.atlas.model.PublishingCompany;

import java.util.List;
import java.util.Optional;

public interface PublishingCompanyDAO {
    int insert(PublishingCompany publishingCompany);

    int update(int pcid, PublishingCompany publishingCompany);

    int delete(int pcid);

    Optional<PublishingCompany> get(int pcid);

    List<PublishingCompany> getAll();
}
