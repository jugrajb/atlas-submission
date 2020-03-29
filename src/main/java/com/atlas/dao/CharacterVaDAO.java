package com.atlas.dao;

import com.atlas.model.CharacterVa;

import java.util.List;

public interface CharacterVaDAO {
    List<CharacterVa> getById(int gid);

    List<CharacterVa> getAll();
}
