package com.atlas.dao;

import com.atlas.model.VoicePerformanceBy;

import java.util.List;
import java.util.Optional;

public interface VoicePerformanceByDAO {
    int insert(VoicePerformanceBy voicePerformanceBy);

    int update(int cid, int pid, VoicePerformanceBy voicePerformanceBy);

    int delete(int cid, int pid);

    Optional<VoicePerformanceBy> get(int cid, int pid);

    List<VoicePerformanceBy> getAll();
}
