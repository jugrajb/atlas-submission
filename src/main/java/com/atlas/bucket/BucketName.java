package com.atlas.bucket;

public enum BucketName {
    IMAGES("cpsc-304-vgdb-atlas-images"),
    USER_PROFILE_IMAGES("user-profile-images"),
    GAME_THUMBNAILS("game-thumbnails"),
    GAME_SCREEN_SHOTS("game-screenshots");

    private final String bucketName;

    BucketName(String bucketName) {
      this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
