package com.artdevs.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.ImageOfPost;

public interface ImageOfPostService {
    ImageOfPost findImageOfPostById(Integer imageofpostId);

    List<ImageOfPost> findAll();

    ImageOfPost saveImageOfPost(String postId, MultipartFile file) throws Exception;

    ImageOfPost updateImageOfPost(ImageOfPost imageofpost);
    
    ImageOfPost findImageOfPostByUrl(String imageUrl);

    void deleteImageOfPost(ImageOfPost imageofpost);
}
