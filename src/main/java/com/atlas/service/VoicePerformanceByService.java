package com.atlas.service;

import com.atlas.dao.VoicePerformanceByDAO;
import com.atlas.model.VoicePerformanceBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoicePerformanceByService {
    private VoicePerformanceByDAO voicePerformanceByDAO;

    @Autowired
    public VoicePerformanceByService(@Qualifier("postgres-voiceperformanceby") VoicePerformanceByDAO voicePerformanceByDAO) {
        this.voicePerformanceByDAO = voicePerformanceByDAO;
    }

    public int add(VoicePerformanceBy voicePerformanceBy) {
        return voicePerformanceByDAO.insert(voicePerformanceBy);
    }

    public int update(int cid, int pid, VoicePerformanceBy voicePerformanceBy) {
        return voicePerformanceByDAO.update(cid, pid, voicePerformanceBy);
    }

    public int delete(int cid, int pid) {
        return voicePerformanceByDAO.delete(cid, pid);
    }

    public Optional<VoicePerformanceBy> get(int cid, int pid) {
        return voicePerformanceByDAO.get(cid, pid);
    }

    public List<VoicePerformanceBy> getAll() {
        return voicePerformanceByDAO.getAll();
    }
}
