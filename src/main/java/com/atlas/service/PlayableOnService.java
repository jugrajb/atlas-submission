package com.atlas.service;

import com.atlas.dao.PlayableOnDAO;
import com.atlas.model.GamePlatform;
import com.atlas.model.PlayableOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayableOnService {
    private PlayableOnDAO playableOnDAO;

    @Autowired
    public PlayableOnService(@Qualifier("postgres-playableon") PlayableOnDAO playableOnDAO) {
        this.playableOnDAO = playableOnDAO;
    }

    public int add(PlayableOn playableOn) {
        return playableOnDAO.insert(playableOn);
    }

    public int update(int gid, int gpid, PlayableOn playableOn) {
        return playableOnDAO.update(gid, gpid, playableOn);
    }

    public int delete(int gid, int gpid) {
        return playableOnDAO.delete(gid, gpid);
    }

    public Optional<PlayableOn> get(int gid, int gpid) {
        return playableOnDAO.get(gid, gpid);
    }

    public List<PlayableOn> getAll() {
        return playableOnDAO.getAll();
    }

    public List<GamePlatform> getAllPlatformsForVideoGame(int gid) {
      return playableOnDAO.getAllPlatformsForVideoGame(gid);
    }
}
