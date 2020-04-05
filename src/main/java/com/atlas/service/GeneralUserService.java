package com.atlas.service;

import com.atlas.bucket.BucketName;
import com.atlas.dao.GeneralUserDAO;
import com.atlas.filestore.FileStore;
import com.atlas.model.GeneralUser;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class GeneralUserService {
    private final GeneralUserDAO generalUserDAO;
    private final FileStore fileStore;

    @Autowired
    public GeneralUserService(@Qualifier("postgres-generaluser") GeneralUserDAO generalUserDAO, FileStore fileStore) {
        this.generalUserDAO = generalUserDAO;
        this.fileStore = fileStore;
    }

    public int add(GeneralUser generalUser) {
        return generalUserDAO.insert(generalUser);
    }

    public List<GeneralUser> getAll() {
        return generalUserDAO.getAll();
    }

    public Optional<GeneralUser> get(int id) {
        return generalUserDAO.get(id);
    }

    public int delete(int id) {
        return generalUserDAO.delete(id);
    }

    public int update(int id, GeneralUser generalUser) {
        return generalUserDAO.update(id, generalUser);
    }

    public void uploadUserProfileImage(int id, MultipartFile file) {
        if(file.isEmpty())
            throw new IllegalStateException("File cannot be empty");

        if(!Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()
        ).contains(file.getContentType()))
            throw new IllegalStateException("Invalid File Type");

        Optional<GeneralUser> user = get(id);

        if(user.isEmpty())
            throw new IllegalStateException("Invalid User ID");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.IMAGES.getBucketName(), BucketName.USER_PROFILE_IMAGES.getBucketName());
        String filename = String.format("%s-%s",UUID.randomUUID(), file.getOriginalFilename());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            update(id, new GeneralUser(id, user.get().getUsername(), filename));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadUserProfileImage(int id) {
        Optional<GeneralUser> generalUser = get(id);

        if(generalUser.isEmpty())
            throw new IllegalStateException("Invalid User ID");

        String path = String.format(
                "%s/%s",
                BucketName.IMAGES.getBucketName(),
                BucketName.USER_PROFILE_IMAGES.getBucketName()
        );

        return fileStore.download(path, generalUser.get().getProfileImage());
    }
}
