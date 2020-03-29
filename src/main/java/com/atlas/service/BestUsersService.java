package com.atlas.service;

import com.atlas.dao.BestUsersDAO;
import com.atlas.model.GeneralUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestUsersService {
    private BestUsersDAO bestUsersDAO;

    @Autowired
    public BestUsersService(@Qualifier("postgres-bestusers") BestUsersDAO bestUsersDAO) {
        this.bestUsersDAO = bestUsersDAO;
    }

    public List<GeneralUser> getAll() {
        return bestUsersDAO.getAll();
    }
}
