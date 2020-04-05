package com.atlas.service;

import com.atlas.bucket.BucketName;
import com.atlas.dao.GamePhotosDAO;
import com.atlas.dao.VideoGameDAO;
import com.atlas.filestore.FileStore;
import com.atlas.model.GamePhotos;
import com.atlas.model.VideoGame;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class GamePhotosService {
    private final GamePhotosDAO gamePhotosDAO;
    private final VideoGameDAO videoGameDAO;
    private final FileStore fileStore;

    @Autowired
    public GamePhotosService(@Qualifier("postgres-gamephotos") GamePhotosDAO gamePhotosDAO,
                             @Qualifier("postgres-videogame") VideoGameDAO videoGameDAO,
                             FileStore fileStore
    ) {
        this.gamePhotosDAO = gamePhotosDAO;
        this.videoGameDAO = videoGameDAO;
        this.fileStore = fileStore;
    }

    public int add(GamePhotos gamePhotos) {
        return gamePhotosDAO.insert(gamePhotos);
    }

    public List<GamePhotos> getAll() {
        return gamePhotosDAO.getAll();
    }

    public Optional<GamePhotos> get(int id) {
        return gamePhotosDAO.get(id);
    }

    public int delete(int id) {
        return gamePhotosDAO.delete(id);
    }

    public int update(int id, GamePhotos gamePhotos) {
        return gamePhotosDAO.update(id, gamePhotos);
    }

    public void uploadCover(int id, MultipartFile file) {
        if(file.isEmpty())
            throw new IllegalStateException("File cannot be empty");

        if(!Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()
        ).contains(file.getContentType()))
            throw new IllegalStateException("Invalid File Type");


        Optional<VideoGame> game = videoGameDAO.get(id);
        Optional<GamePhotos> gamePhotos = get(id);

        if(game.isEmpty())
            throw new IllegalStateException("Invalid Game ID");

        if(gamePhotos.isEmpty())
            add(new GamePhotos(id, "", ""));

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.IMAGES.getBucketName(), BucketName.GAME_THUMBNAILS.getBucketName());
        String filename = String.format("%s", file.getOriginalFilename());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            update(id, new GamePhotos(id, filename, gamePhotos.get().getBanner()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void uploadBanner(int id, MultipartFile file) {
        if(file.isEmpty())
            throw new IllegalStateException("File cannot be empty");

        if(!Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()
        ).contains(file.getContentType()))
            throw new IllegalStateException("Invalid File Type");


        Optional<VideoGame> game = videoGameDAO.get(id);
        Optional<GamePhotos> gamePhotos = get(id);

        if(game.isEmpty())
            throw new IllegalStateException("Invalid Game ID");

        if(gamePhotos.isEmpty())
            add(new GamePhotos(id, "", ""));

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.IMAGES.getBucketName(), BucketName.GAME_SCREEN_SHOTS.getBucketName());
        String filename = String.format("%s", file.getOriginalFilename());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            update(id, new GamePhotos(id, gamePhotos.get().getCover(), filename));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadCoverImage(int id) {
        Optional<GamePhotos> gamePhotos = get(id);

        if(gamePhotos.isEmpty())
            throw new IllegalStateException("Invalid Game ID");

        String path = String.format(
                "%s/%s",
                BucketName.IMAGES.getBucketName(),
                BucketName.GAME_THUMBNAILS.getBucketName()
        );

        return fileStore.download(path, gamePhotos.get().getCover());
    }

    public byte[] downloadBannerImage(int id) {
        Optional<GamePhotos> gamePhotos = get(id);

        if(gamePhotos.isEmpty())
            throw new IllegalStateException("Invalid Game ID");

        String path = String.format(
                "%s/%s",
                BucketName.IMAGES.getBucketName(),
                BucketName.GAME_SCREEN_SHOTS.getBucketName()
        );

        return fileStore.download(path, gamePhotos.get().getBanner());
    }
}
