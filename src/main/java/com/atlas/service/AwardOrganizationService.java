package com.atlas.service;

import com.atlas.dao.AwardOrganizationDAO;
import com.atlas.model.AwardOrganization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AwardOrganizationService {
    private AwardOrganizationDAO awardOrganizationDAO;

    public AwardOrganizationService(@Qualifier("postgres-awardorganization")
                                            AwardOrganizationDAO awardOrganizationDAO) {
        this.awardOrganizationDAO = awardOrganizationDAO;
    }

    public int add(AwardOrganization awardOrganization) {
        return awardOrganizationDAO.insert(awardOrganization);
    }

    public int update(int oid, AwardOrganization awardOrganization) {
        return awardOrganizationDAO.update(oid, awardOrganization);
    }

    public int delete(int oid) {
        return awardOrganizationDAO.delete(oid);
    }

    public Optional<AwardOrganization> get(int oid) {
        return awardOrganizationDAO.get(oid);
    }

    public List<AwardOrganization> getAll() {
        return awardOrganizationDAO.getAll();
    }
}
