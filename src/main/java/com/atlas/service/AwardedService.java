package com.atlas.service;

import com.atlas.dao.AwardedDAO;
import com.atlas.model.Awarded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AwardedService {
    private AwardedDAO awardedDAO;

    @Autowired
    public AwardedService(@Qualifier("postgres-awarded") AwardedDAO awardedDAO) {
        this.awardedDAO = awardedDAO;
    }

    public int add(Awarded awarded) {
        return awardedDAO.insert(awarded);
    }

    public int update(String name, int oid, int gid, Awarded awarded) {
        return awardedDAO.update(name, oid, gid, awarded);
    }

    public int delete(String name, int oid, int gid) {
        return awardedDAO.delete(name, oid, gid);
    }

    public Optional<Awarded> get(String name, int oid, int gid) {
        return awardedDAO.get(name, oid, gid);
    }

    public List<Awarded> getAll() {
        return awardedDAO.getAll();
    }
}
