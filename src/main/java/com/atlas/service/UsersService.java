package com.atlas.service;

import com.atlas.dao.UsersDAO;
import com.atlas.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersDAO usersDAO;

    @Autowired
    public UsersService(@Qualifier("postgres-users") UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public int add(Users users) {
        return usersDAO.insert(users);
    }

    public List<Users> getAll() {
        return usersDAO.getAll();
    }

    public Optional<Users> get(int id) {
        return usersDAO.get(id);
    }

    public int delete(int id) {
        return usersDAO.delete(id);
    }

    public int update(int id, Users users) {
        return usersDAO.update(id, users);
    }
}
