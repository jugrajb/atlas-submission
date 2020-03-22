package com.atlas.dao;

import com.atlas.model.Administrator;

import java.util.List;
import java.util.Optional;

public interface AdministratorDAO {
    int insert(Administrator administrator);

    int update(int id, Administrator administrator);

    int delete(int id);

    Optional<Administrator> get(int id);

    List<Administrator> getAll();
}
