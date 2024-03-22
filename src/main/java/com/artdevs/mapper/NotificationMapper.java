package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Notification;
import com.artdevs.dto.post.UserPostDTO;
import com.artdevs.dto.user.NotificationDTO;
import com.artdevs.dto.user.NotificationToGetDTO;
import com.artdevs.utils.Global;

public class NotificationMapper {

    private static final ModelMapper modelMapper = new ModelMapper();
    
    public static NotificationDTO convertToDto(Notification notification) {
    	NotificationDTO notificationDTO = modelMapper.map(notification, NotificationDTO.class);
//    	notificationDTO.setReceiver(notification.getReceiver().getUserId());
//    	notificationDTO.setSender(notification.getSender().getUserId());
    	return notificationDTO;
    }
    
    public static NotificationToGetDTO convertToGetDto(Notification notification) {
    	NotificationToGetDTO notificationDTOGetDTO = modelMapper.map(notification, NotificationToGetDTO.class);
    	notificationDTOGetDTO.setSenderId(new UserPostDTO(
			    Global.safeTrim(notification.getSender().getUserId()),
			    Global.safeTrim(notification.getSender().getUsername()),
			    Global.safeTrim(UserMapper.getAvatar(notification.getSender(), true)),
			    String.join(" ", 
			    		Global.safeTrim(notification.getSender().getFirstName()), 
			    		Global.safeTrim(notification.getSender().getMiddleName()), 
			    		Global.safeTrim(notification.getSender().getLastName())
			    )
			));
    	notificationDTOGetDTO.setReceiverId(new UserPostDTO(
			    Global.safeTrim(notification.getReceiver().getUserId()),
			    Global.safeTrim(notification.getReceiver().getUsername()),
			    Global.safeTrim(UserMapper.getAvatar(notification.getReceiver(), true)),
			    String.join(" ", 
			    		Global.safeTrim(notification.getReceiver().getFirstName()), 
			    		Global.safeTrim(notification.getReceiver().getMiddleName()), 
			    		Global.safeTrim(notification.getReceiver().getLastName())
			    )
			));
    	return notificationDTOGetDTO;
    }
}
