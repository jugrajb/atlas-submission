package com.atlas.service;

import com.atlas.dao.NaturalJoinDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NaturalJoinService {
    private NaturalJoinDAO naturalJoinDAO;

    @Autowired
    public NaturalJoinService(@Qualifier("postgres-naturaljoin") NaturalJoinDAO naturalJoinDAO) {
        this.naturalJoinDAO = naturalJoinDAO;
    }

    public List<Map<String, Object>> get(String relation1, String relation2) {
        return naturalJoinDAO.get(relation1, relation2);
    }
}
