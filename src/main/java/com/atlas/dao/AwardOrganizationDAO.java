package com.atlas.dao;

import com.atlas.model.AwardOrganization;

import java.util.List;
import java.util.Optional;

public interface AwardOrganizationDAO {
    int insert(AwardOrganization awardOrganization);

    int update(int oid, AwardOrganization awardOrganization);

    int delete(int oid);

    Optional<AwardOrganization> get(int oid);

    List<AwardOrganization> getAll();
}
