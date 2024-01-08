package com.artdevs.mapper.message;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.dto.message.MessageDTO;

public class MessageMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static MessageDTO convertToMessageDTO(Message message) {
        MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
        return messageDTO;
    }

    public static Message convertToMessage(MessageDTO messageDTO) {
        Message message = modelMapper.map(messageDTO, Message.class);
        return message;
    }
}
