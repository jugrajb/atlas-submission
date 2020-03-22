package com.atlas.service;

import com.atlas.dao.AdministratorDAO;
import com.atlas.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    private final AdministratorDAO administratorDAO;

    @Autowired
    public AdministratorService(@Qualifier("postgres-administrator") AdministratorDAO administratorDAO) {
        this.administratorDAO = administratorDAO;
    }

    public int add(Administrator administrator) {
        return administratorDAO.insert(administrator);
    }

    public List<Administrator> getAll() {
        return administratorDAO.getAll();
    }

    public Optional<Administrator> get(int id) {
        return administratorDAO.get(id);
    }

    public int delete(int id) {
        return administratorDAO.delete(id);
    }

    public int update(int id, Administrator administrator) {
        return administratorDAO.update(id, administrator);
    }
}
