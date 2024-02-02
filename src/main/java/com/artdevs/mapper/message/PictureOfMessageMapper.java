package com.artdevs.mapper.message;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.message.PictureOfMessage;
import com.artdevs.dto.message.PictureOfMessageDTO;

public class PictureOfMessageMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static PictureOfMessageDTO convertToPictureOfMessageDTO(PictureOfMessage pictureOfMessage) {
        PictureOfMessageDTO pictureOfMessageDTO = modelMapper.map(pictureOfMessage, PictureOfMessageDTO.class);

        return pictureOfMessageDTO;
    }

    public static PictureOfMessage convertToPictureOfMessage(PictureOfMessageDTO pictureOfMessageDTO) {
        PictureOfMessage pictureOfMessage = modelMapper.map(pictureOfMessageDTO, PictureOfMessage.class);
        return pictureOfMessage;
    }
}
