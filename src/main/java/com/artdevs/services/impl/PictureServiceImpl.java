package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.repositories.user.PictureRepository;
import com.artdevs.services.PictureService;

public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Override
    public Picture findPictureById(Integer pictureId) {
        Optional<Picture> pictureOptional = pictureRepository.findById(pictureId);
        return pictureOptional.orElse(null);
    }

    @Override
    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Override
    public Picture savePicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Picture updatePicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public void deletePicture(Picture picture) {
        pictureRepository.delete(picture);
    }

}
