package com.atlas.das;

import com.atlas.dao.VideoGamePersonDAO;
import com.atlas.model.VideoGamePerson;

import java.util.List;
import java.util.Optional;

public class VideoGamePersonDAS implements VideoGamePersonDAO {
    @Override
    public int insertVideoGamePerson(VideoGamePerson person) {
        return 0;
    }

    @Override
    public int deleteVideoGamePerson(VideoGamePerson person) {
        return 0;
    }

    @Override
    public int updateVideoGamePerson(int pid, VideoGamePerson person) {
        return 0;
    }

    @Override
    public Optional<VideoGamePerson> getVideoGamePerson(int pid) {
        return Optional.empty();
    }

    @Override
    public List<VideoGamePerson> getAllVideoGamePeople() {
        return null;
    }
}
