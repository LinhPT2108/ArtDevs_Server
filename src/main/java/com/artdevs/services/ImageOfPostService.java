package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.ImageOfPost;

public interface ImageOfPostService {
    ImageOfPost findImageOfPostById(Integer imageofpostId);

    List<ImageOfPost> findAll();

    ImageOfPost saveImageOfPost(ImageOfPost imageofpost);

    ImageOfPost updateImageOfPost(ImageOfPost imageofpost);

    void deleteImageOfPost(ImageOfPost imageofpost);
}
