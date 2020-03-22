package com.atlas.dao;

import com.atlas.model.VideoGame;

import java.util.List;
import java.util.Optional;

public interface VideoGameDAO {

  int insert(VideoGame videoGame);

  int update(int id, VideoGame videoGame);

  int delete(int id);

  Optional<VideoGame> get(int id);

  List<VideoGame> getAll();
}
