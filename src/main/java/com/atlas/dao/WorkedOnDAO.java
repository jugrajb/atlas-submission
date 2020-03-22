package com.atlas.dao;

import com.atlas.model.WorkedOn;

import java.util.List;
import java.util.Optional;

public interface WorkedOnDAO {
    int insert(WorkedOn workedOn);

    int update(int gid, int pid, WorkedOn workedOn);

    int delete(int gid, int pid);

    Optional<WorkedOn> get(int gid, int pid);

    List<WorkedOn> getAll();
}
