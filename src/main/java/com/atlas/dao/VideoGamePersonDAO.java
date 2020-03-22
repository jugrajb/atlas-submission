package com.atlas.dao;

import com.atlas.model.VideoGamePerson;

import java.util.List;
import java.util.Optional;

public interface VideoGamePersonDAO {
    int insert(VideoGamePerson person);

    int delete(int id);

    int update(int pid, VideoGamePerson person);

    Optional<VideoGamePerson> get(int pid);

    List<VideoGamePerson> getAll();
}
