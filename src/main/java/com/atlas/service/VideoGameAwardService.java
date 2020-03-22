package com.atlas.service;

import com.atlas.dao.VideoGameAwardDAO;
import com.atlas.model.VideoGameAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoGameAwardService {

    private VideoGameAwardDAO videoGameAwardDAO;

    @Autowired
    public VideoGameAwardService(@Qualifier("postgres-videogameaward") VideoGameAwardDAO videoGameAwardDAO) {
        this.videoGameAwardDAO = videoGameAwardDAO;
    }

    public int add(VideoGameAward videoGameAward) {
        return videoGameAwardDAO.insert(videoGameAward);
    }

    public int update(String name, int oid, VideoGameAward videoGameAward) {
        return videoGameAwardDAO.update(name, oid, videoGameAward);
    }

    public int delete(String name, int oid) {
        return videoGameAwardDAO.delete(name, oid);
    }

    public Optional<VideoGameAward> get(String name, int oid) {
        return videoGameAwardDAO.get(name, oid);
    }

    public List<VideoGameAward> getAll() {
        return videoGameAwardDAO.getAll();
    }
}
