package com.atlas.service;

import com.atlas.dao.DirectorDAO;
import com.atlas.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private final DirectorDAO directorDAO;

    @Autowired
    public DirectorService(@Qualifier("postgres-director") DirectorDAO directorDAO) {
        this.directorDAO = directorDAO;
    }

    public int add(Director director) {
        return directorDAO.insert(director);
    }

    public List<Director> getAll() {
        return directorDAO.getAll();
    }

    public Optional<Director> get(int id) {
        return directorDAO.get(id);
    }

    public int delete(int id) {
        return directorDAO.delete(id);
    }

    public int update(int id, Director director) {
        return directorDAO.update(id, director);
    }
}
