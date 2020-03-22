package com.atlas.service;

import com.atlas.dao.GeneralUserDAO;
import com.atlas.model.GeneralUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralUserService {
    private final GeneralUserDAO generalUserDAO;

    @Autowired
    public GeneralUserService(@Qualifier("postgres-generaluser") GeneralUserDAO generalUserDAO) {
        this.generalUserDAO = generalUserDAO;
    }

    public int add(GeneralUser generalUser) {
        return generalUserDAO.insert(generalUser);
    }

    public List<GeneralUser> getAll() {
        return generalUserDAO.getAll();
    }

    public Optional<GeneralUser> get(int id) {
        return generalUserDAO.get(id);
    }

    public int delete(int id) {
        return generalUserDAO.delete(id);
    }

    public int update(int id, GeneralUser generalUser) {
        return generalUserDAO.update(id, generalUser);
    }
}
