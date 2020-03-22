package com.atlas.dao;

import com.atlas.model.GeneralUser;

import java.util.List;
import java.util.Optional;

public interface GeneralUserDAO {
    int insert(GeneralUser generalUser);

    int update(int id, GeneralUser generalUser);

    int delete(int id);

    Optional<GeneralUser> get(int id);

    List<GeneralUser> getAll();
}
