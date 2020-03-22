package com.atlas.service;

import com.atlas.dao.AppearsInDAO;
import com.atlas.model.AppearsIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppearsInService {

    private AppearsInDAO appearsInDAO;

    @Autowired
    public AppearsInService(@Qualifier("postgres-appearsin") AppearsInDAO appearsInDAO) {
        this.appearsInDAO = appearsInDAO;
    }

    public int add(AppearsIn appearsIn) {
        return appearsInDAO.insert(appearsIn);
    }

    public int update(int gid, int cid, AppearsIn appearsIn) {
        return appearsInDAO.update(gid, cid, appearsIn);
    }

    public int delete(int gid, int cid) {
        return appearsInDAO.delete(gid, cid);
    }

    public Optional<AppearsIn> get(int gid, int cid) {
        return appearsInDAO.get(gid, cid);
    }

    public List<AppearsIn> getAll() {
        return  appearsInDAO.getAll();
    }
}
