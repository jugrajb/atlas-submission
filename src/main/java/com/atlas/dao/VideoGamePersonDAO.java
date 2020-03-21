package com.atlas.dao;

import com.atlas.model.VideoGamePerson;

import java.util.List;
import java.util.Optional;

public interface VideoGamePersonDAO {

    int insertVideoGamePerson(VideoGamePerson person);

    int deleteVideoGamePerson(int id);

    int updateVideoGamePerson(int pid, VideoGamePerson person);

    Optional<VideoGamePerson> getVideoGamePerson(int pid);

    List<VideoGamePerson> getAllVideoGamePeople();
}
