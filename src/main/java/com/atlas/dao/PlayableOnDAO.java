package com.atlas.dao;

import com.atlas.model.GamePlatform;
import com.atlas.model.PlayableOn;

import java.util.List;
import java.util.Optional;

public interface PlayableOnDAO {
    int insert(PlayableOn playableOn);

    int update(int gid, int gpid, PlayableOn playableOn);

    int delete(int gid, int gpid);

    Optional<PlayableOn> get(int gid, int gpid);

    List<PlayableOn> getAll();

    List<GamePlatform> getAllPlatformsForVideoGame(int gid);
}
