package com.atlas.dao;

import com.atlas.model.VideoGame;

import java.util.List;
import java.util.Optional;

public interface VideoGameDAO {

  int insertVideoGame(VideoGame videoGame);

  int updateVideoGame(int id, VideoGame videoGame);

  int deleteVideoGame(int id);

  Optional<VideoGame> getVideoGame(int id);

  List<VideoGame> getAllVideoGame();
}
