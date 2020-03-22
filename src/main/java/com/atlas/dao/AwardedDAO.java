package com.atlas.dao;

import com.atlas.model.Awarded;

import java.util.List;
import java.util.Optional;

public interface AwardedDAO {
    int insert(Awarded awarded);

    int update(String name, int oid, int gid, Awarded awarded);

    int delete(String name, int oid, int gid);

    Optional<Awarded> get(String name, int oid, int gid);

    List<Awarded> getAll();
}
