package com.atlas.dao;

import com.atlas.model.AppearsIn;
import com.atlas.model.InGameCharacter;

import java.util.List;
import java.util.Optional;

public interface AppearsInDAO {
    int insert(AppearsIn appearsIn);

    int update(int gid, int cid, AppearsIn appearsIn);

    int delete(int gid, int cid);

    Optional<AppearsIn> get(int gid, int cid);

    List<AppearsIn> getAll();
}
