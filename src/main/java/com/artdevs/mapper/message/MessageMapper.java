package com.artdevs.mapper.message;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.message.PictureOfMessage;
import com.artdevs.dto.message.MessageDTO;
import com.artdevs.dto.message.PictureOfMessageDTO;

public class MessageMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static MessageDTO convertToMessageDTO(Message message) {
        MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
        messageDTO.setRelationShipId(message.getRelationShipId().getId());
        messageDTO.setFormUserId(message.getFormUserId().getUserId());
        messageDTO.setToUserId(message.getToUserId().getUserId());
        messageDTO.setPictureOfMessages(getPictureOfMessage(message));
        return messageDTO;
    }

    public static Message convertToMessage(MessageDTO messageDTO) {
        Message message = modelMapper.map(messageDTO, Message.class);
        return message;
    }

    private static List<PictureOfMessageDTO> getPictureOfMessage(Message message) {
        List<PictureOfMessageDTO> pictureOfMessageDTO = new ArrayList<>();
        List<PictureOfMessage> pictureOfMessages = message.getPictureOfMessages();
        for (PictureOfMessage pictureOfMessage : pictureOfMessages) {
            pictureOfMessageDTO.add(PictureOfMessageMapper.convertToPictureOfMessageDTO(pictureOfMessage));
        }
        return pictureOfMessageDTO;
    }
}
