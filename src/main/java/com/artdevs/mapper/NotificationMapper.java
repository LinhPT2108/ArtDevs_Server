package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Notification;
import com.artdevs.dto.user.NotificationDTO;

public class NotificationMapper {

    private static final ModelMapper modelMapper = new ModelMapper();
    
    public static NotificationDTO convertToDto(Notification notification) {
    	NotificationDTO notificationDTO = modelMapper.map(notification, NotificationDTO.class);
//    	notificationDTO.setReceiver(notification.getReceiver().getUserId());
//    	notificationDTO.setSender(notification.getSender().getUserId());
    	return notificationDTO;
    }
}
