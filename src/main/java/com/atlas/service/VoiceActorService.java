package com.atlas.service;

import com.atlas.dao.VoiceActorDAO;
import com.atlas.model.VoiceActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoiceActorService {
    private final VoiceActorDAO voiceActorDAO;

    @Autowired
    public VoiceActorService(@Qualifier("postgres-voiceactor") VoiceActorDAO voiceActorDAO) {
        this.voiceActorDAO = voiceActorDAO;
    }

    public int add(VoiceActor voiceActor) {
        return voiceActorDAO.insert(voiceActor);
    }

    public List<VoiceActor> getAll() {
        return voiceActorDAO.getAll();
    }

    public Optional<VoiceActor> get(int id) {
        return voiceActorDAO.get(id);
    }

    public int delete(int id) {
        return voiceActorDAO.delete(id);
    }

    public int update(int id, VoiceActor voiceActor) {
        return voiceActorDAO.update(id, voiceActor);
    }
}
