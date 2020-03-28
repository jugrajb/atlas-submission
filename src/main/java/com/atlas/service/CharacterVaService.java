package com.atlas.service;

import com.atlas.dao.CharacterVaDAO;
import com.atlas.model.CharacterVa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterVaService {
    private CharacterVaDAO characterVaDAO;

    @Autowired
    public CharacterVaService(@Qualifier("postgres-characterva") CharacterVaDAO characterVaDAO) {
        this.characterVaDAO = characterVaDAO;
    }

    public List<CharacterVa> getById(int gid) {
        return characterVaDAO.getById(gid);
    }

    public List<CharacterVa> getAll() {
        return characterVaDAO.getAll();
    }
}
