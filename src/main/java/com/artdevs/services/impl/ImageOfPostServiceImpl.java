package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.repositories.post.ImageofpostRepository;
import com.artdevs.services.ImageOfPostService;

public class ImageOfPostServiceImpl implements ImageOfPostService {

    @Autowired
    ImageofpostRepository imageofpostRepository;

    @Override
    public ImageOfPost findImageOfPostById(Integer imageofpostId) {
        Optional<ImageOfPost> imageOfPostOptional = imageofpostRepository.findById(imageofpostId);
        return imageOfPostOptional.orElse(null);
    }

    @Override
    public List<ImageOfPost> findAll() {
        return imageofpostRepository.findAll();
    }

    @Override
    public ImageOfPost saveImageOfPost(ImageOfPost imageofpost) {
        return imageofpostRepository.save(imageofpost);
    }

    @Override
    public ImageOfPost updateImageOfPost(ImageOfPost imageofpost) {
        return imageofpostRepository.save(imageofpost);
    }

    @Override
    public void deleteImageOfPost(ImageOfPost imageofpost) {
        imageofpostRepository.delete(imageofpost);
    }
}
