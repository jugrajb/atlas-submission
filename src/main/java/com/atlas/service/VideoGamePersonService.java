package com.atlas.service;

import com.atlas.dao.VideoGameDAO;
import com.atlas.dao.VideoGamePersonDAO;
import com.atlas.model.VideoGame;
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

    public int addVideoGame(VideoGamePerson videoGamePerson) {
        return videoGamePersonDAO.insertVideoGamePerson(videoGamePerson);
    }

    public List<VideoGamePerson> getAllVideoGamePeople() {
        return videoGamePersonDAO.getAllVideoGamePeople();
    }

    public Optional<VideoGamePerson> getVideoGameById(int id) {
        return videoGamePersonDAO.getVideoGamePerson(id);
    }

    public int deleteVideoGame(int id) {
        return videoGamePersonDAO.deleteVideoGamePerson(id);
    }

    public int updateVideoGame(int id, VideoGamePerson videoGamePerson) {
        return videoGamePersonDAO.updateVideoGamePerson(id, videoGamePerson);
    }
}
