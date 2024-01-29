package com.artdevs.mapper.message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.message.MessageDTO;
import com.artdevs.dto.message.RelationShipDTO;
import com.artdevs.services.UserService;

public class RelationShipMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static RelationShipDTO convertToRelationShipDTO(RelationShip relationShip) {

        RelationShipDTO relationShipDTO = new RelationShipDTO();
        relationShipDTO.setId(relationShip.getId());
        relationShipDTO.setTimeRelation(relationShip.getTimeRelation());
        relationShipDTO.setUserActionID(relationShip.getActionUser().getUserId());
        relationShipDTO.setUserID1(relationShip.getUserOneId().getUserId());
        relationShipDTO.setUserID2(relationShip.getUserTwoId().getUserId());
        return relationShipDTO;
    }

    // public static RelationShipDTO convertToRelationShipDTO(RelationShip
    // relationShip) {
    // modelMapper.typeMap(RelationShip.class, RelationShipDTO.class)
    // .addMapping(src -> src.getUserOneId().getUserId(),
    // RelationShipDTO::setUserID1)
    // .addMapping(src -> src.getUserTwoId().getUserId(),
    // RelationShipDTO::setUserID2);
    //
    // RelationShipDTO relationShipDTO = modelMapper.map(relationShip,
    // RelationShipDTO.class);
    //
    // // Các ánh xạ hoặc điều chỉnh bổ sung nếu cần thiết
    // relationShipDTO.setUserActionID(null);
    // relationShipDTO.setMessageID(null);
    //
    // return relationShipDTO;
    // }

    public static RelationShip convertToRelationShip(RelationShipDTO relationShipDTO, UserService userservice) {
        RelationShip relationShip = modelMapper.map(relationShipDTO, RelationShip.class);
        relationShip.setActionUser(setUser(relationShipDTO.getUserActionID(), userservice));
        relationShip.setUserOneId(setUser(relationShipDTO.getUserID1(), userservice));
        relationShip.setUserTwoId(setUser(relationShipDTO.getUserID2(), userservice));
        relationShip.setRelationMessage(null);
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

    // private static List<String> getDemand(User user) {
    // return user.getUserDemand().stream().map(Demand ->
    // Demand.getLanguage().getLanguageName())
    // .collect(Collectors.toList());
    // }
    private static List<String> getMessageID(RelationShip relationship) {
        return relationship.getRelationMessage().stream().map(Message -> Message.getMessageId())
                .collect(Collectors.toList());
    }

    private static User setUser(String userid, UserService userservice) {
        return userservice.findUserById(userid);

    }
}
