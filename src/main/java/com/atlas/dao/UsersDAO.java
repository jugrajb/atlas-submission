package com.atlas.dao;

import com.atlas.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersDAO {
    int insert(Users users);

    int update(int id, Users users);

    int delete(int id);

    Optional<Users> get(int id);

    List<Users> getAll();
}
