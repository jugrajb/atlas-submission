package com.atlas.service;

import com.atlas.dao.PersonGameDAO;
import com.atlas.model.GameByPerson;
import com.atlas.model.PersonByGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonGameService {
    private PersonGameDAO personGameDAO;

    @Autowired
    public PersonGameService(@Qualifier("postgres-persongame") PersonGameDAO personGameDAO) {
        this.personGameDAO = personGameDAO;
    }

    public List<GameByPerson> getGamesByPerson(int pid) {
        return personGameDAO.getGamesByPerson(pid);
    }

    public List<PersonByGame> getPeopleByGame(int gid) {
        return personGameDAO.getPeopleByGame(gid);
    }
}
