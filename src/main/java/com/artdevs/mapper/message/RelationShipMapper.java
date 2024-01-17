package com.artdevs.mapper.message;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.dto.message.MessageDTO;
import com.artdevs.dto.message.RelationShipDTO;

public class RelationShipMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static RelationShipDTO convertToRelationShipDTO(RelationShip relationShip) {
        RelationShipDTO relationShipDTO = modelMapper.map(relationShip, RelationShipDTO.class);
        relationShipDTO.setRelationShipActionUser(relationShip.getActionUser().getUserId());
        relationShipDTO.setRelationShipUserOneId(relationShip.getUserOneId().getUserId());
        relationShipDTO.setRelatioShipUserTwoId(relationShip.getUserTwoId().getUserId());
        relationShipDTO.setListRelationMessage(getMessage(relationShip));
        return relationShipDTO;
    }

    public static RelationShip convertToRelationShip(RelationShipDTO relationShipDTO) {
        RelationShip relationShip = modelMapper.map(relationShipDTO, RelationShip.class);
        return relationShip;
    }

    private static List<MessageDTO> getMessage(RelationShip relationShip) {
        List<MessageDTO> messageDTO = new ArrayList<>();
        List<Message> messages = relationShip.getRelationMessage();
        for (Message message : messages) {
            messageDTO.add(MessageMapper.convertToMessageDTO(message));
        }
        return messageDTO;
    }
}
