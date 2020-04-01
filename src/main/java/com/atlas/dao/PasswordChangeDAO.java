package com.atlas.dao;

import com.atlas.model.PasswordChange;

import java.util.List;
import java.util.Optional;

public interface PasswordChangeDAO {
    List<PasswordChange> getByUser(int uid);

    List<PasswordChange> getAll();
}
