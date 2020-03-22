package com.atlas.service;

import com.atlas.dao.WorkedOnDAO;
import com.atlas.model.WorkedOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkedOnService {
    private WorkedOnDAO workedOnDAO;

    @Autowired
    public WorkedOnService(@Qualifier("postgres-workedon") WorkedOnDAO workedOnDAO) {
        this.workedOnDAO = workedOnDAO;
    }

    public int add(WorkedOn workedOn) {
        return workedOnDAO.insert(workedOn);
    }

    public int update(int gid, int pid, WorkedOn workedOn) {
        return workedOnDAO.update(gid, pid, workedOn);
    }

    public int delete(int gid, int pid) {
        return workedOnDAO.delete(gid, pid);
    }

    public Optional<WorkedOn> get(int gid, int pid) {
        return workedOnDAO.get(gid, pid);
    }

    public List<WorkedOn> getAll() {
        return workedOnDAO.getAll();
    }
}
