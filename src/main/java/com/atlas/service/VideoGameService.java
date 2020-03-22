package com.atlas.service;

import com.atlas.dao.VideoGameDAO;
import com.atlas.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoGameService {
  private final VideoGameDAO videoGameDAO;

  @Autowired
  public VideoGameService(@Qualifier("postgres-videogame") VideoGameDAO videoGameDAO) {
    this.videoGameDAO = videoGameDAO;
  }

  public int add(VideoGame videoGame) {
    return videoGameDAO.insert(videoGame);
  }

  public List<VideoGame> getAll() {
    return videoGameDAO.getAll();
  }

  public Optional<VideoGame> get(int id) {
    return videoGameDAO.get(id);
  }

  public int delete(int id) {
    return videoGameDAO.delete(id);
  }

  public int update(int id, VideoGame videoGame) {
    return videoGameDAO.update(id, videoGame);
  }
}
