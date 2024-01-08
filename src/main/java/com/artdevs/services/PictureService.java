package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.Picture;

public interface PictureService {
    Picture findPictureById(Integer pictureId);

    List<Picture> findAll();

    Picture savePicture(Picture picture);

    Picture updatePicture(Picture picture);

    void deletePicture(Picture picture);
}
