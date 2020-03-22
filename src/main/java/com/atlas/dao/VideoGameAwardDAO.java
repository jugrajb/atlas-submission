package com.atlas.dao;

import com.atlas.model.VideoGameAward;

import java.util.List;
import java.util.Optional;

public interface VideoGameAwardDAO {
    int insert(VideoGameAward videoGameAward);

    int update(String name, int oid, VideoGameAward videoGameAward);

    int delete(String name, int oid);

    Optional<VideoGameAward> get(String name, int oid);

    List<VideoGameAward> getAll();
}
