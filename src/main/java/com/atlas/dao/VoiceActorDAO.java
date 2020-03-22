package com.atlas.dao;

import com.atlas.model.VoiceActor;

import java.util.List;
import java.util.Optional;

public interface VoiceActorDAO {
    int insert(VoiceActor voiceActor);

    int update(int id, VoiceActor voiceActor);

    int delete(int id);

    Optional<VoiceActor> get(int id);

    List<VoiceActor> getAll();
}
