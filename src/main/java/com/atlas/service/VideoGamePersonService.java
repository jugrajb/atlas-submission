package com.atlas.service;

import com.atlas.dao.VideoGamePersonDAO;
import com.atlas.model.VideoGamePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoGamePersonService {
    private final VideoGamePersonDAO videoGamePersonDAO;

    @Autowired
    public VideoGamePersonService(@Qualifier("postgres-videogameperson") VideoGamePersonDAO videoGamePersonDAO) {
        this.videoGamePersonDAO = videoGamePersonDAO;
    }

    public int add(VideoGamePerson videoGamePerson) {
        return videoGamePersonDAO.insert(videoGamePerson);
    }

    public int update(int id, VideoGamePerson videoGamePerson) {
        return videoGamePersonDAO.update(id, videoGamePerson);
    }

    public int delete(int id) {
        return videoGamePersonDAO.delete(id);
    }

    public Optional<VideoGamePerson> get(int id) {
        return videoGamePersonDAO.get(id);
    }

    public List<VideoGamePerson> getAll() {
        return videoGamePersonDAO.getAll();
    }
}
