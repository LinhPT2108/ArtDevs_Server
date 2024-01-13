package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.dto.user.PictureDTO;

public class PictureMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static PictureDTO convertToPictureDTO(Picture picture) {
        PictureDTO pictureDTO = modelMapper.map(picture, PictureDTO.class);
        pictureDTO.setUserId(picture.getUser().getUserId());
        return pictureDTO;
    }

    public static Picture convertToPicture(PictureDTO pictureDTO) {
        Picture picture = modelMapper.map(pictureDTO, Picture.class);
        return picture;
    }
}
