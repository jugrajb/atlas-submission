package com.atlas.dao;

import com.atlas.model.GameByPerson;
import com.atlas.model.PersonByGame;

import java.util.List;

public interface PersonGameDAO {
    List<GameByPerson> getGamesByPerson(int pid);

    List<PersonByGame> getPeopleByGame(int gid);
}
