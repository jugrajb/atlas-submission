package com.atlas.service;

import com.atlas.dao.PasswordChangeDAO;
import com.atlas.model.PasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordChangeService {
    private PasswordChangeDAO passwordChangeDAO;

    @Autowired
    public PasswordChangeService(@Qualifier("postgres-passwordchange") PasswordChangeDAO passwordChangeDAO) {
        this.passwordChangeDAO = passwordChangeDAO;
    }

    public List<PasswordChange> getByUser(int uid) {
        return passwordChangeDAO.getByUser(uid);
    }

    public List<PasswordChange> getAll() {
        return passwordChangeDAO.getAll();
    }

}
