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
  public VideoGameService(@Qualifier("postgres") VideoGameDAO videoGameDAO) {
    this.videoGameDAO = videoGameDAO;
  }

  public int addVideoGame(VideoGame videoGame) {
    return videoGameDAO.insertVideoGame(videoGame);
  }

  public List<VideoGame> getAllVideoGame() {
    return videoGameDAO.getAllVideoGame();
  }

  public Optional<VideoGame> getVideoGameById(int id) {
    return videoGameDAO.getVideoGame(id);
  }

  public int deleteVideoGame(int id) {
    return videoGameDAO.deleteVideoGame(id);
  }

  public int updateVideoGame(int id, VideoGame videoGame) {
    return videoGameDAO.updateVideoGame(id, videoGame);
  }
}
