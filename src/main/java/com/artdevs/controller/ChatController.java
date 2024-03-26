package com.artdevs.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.artdevs.domain.entities.user.Notification;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.user.NotificationDTO;
import com.artdevs.mapper.NotificationMapper;
import com.artdevs.services.NotificationService;
import com.artdevs.services.UserService;

@Controller
public class ChatController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private NotificationService notificationService;

	@MessageMapping("/send-notification/{userId}")
	public void sendMessageToUser(@Payload NotificationDTO notificationDTO, @DestinationVariable String userId) {

		System.out.println(notificationDTO);
		System.out.println(userId);
		User user = userService.findUserById(userId);
		Notification notificationSave = new Notification();
		notificationSave.setCreateDate(new Date());
		notificationSave.setMessage(notificationDTO.getMessage());
		notificationSave.setSender(userService.findUserById(notificationDTO.getSenderId()));
		notificationSave.setReceiver(userService.findUserById(notificationDTO.getReceiverId()));
		notificationSave.setRead(false);
		notificationSave.setType(notificationDTO.getType());
		notificationSave.setPostId(notificationDTO.getPostId());
		notificationSave.setShareId(notificationDTO.getShareId());
		Notification notificationSaveReturn = notificationService.createNotification(notificationSave);

		messagingTemplate.convertAndSendToUser(userId, "/notification", NotificationMapper.convertToGetDto(notificationSaveReturn));
	}
	
//	@MessageMapping("/send-message/{userId}")
//	public void sendMessageToUser(@Payload MessageToPostDTO messageToPostDTO, @DestinationVariable String userId) {
//		System.out.println(messageToPostDTO.toString());
//		messagingTemplate.convertAndSendToUser(userId, "/message", messageToPostDTO);
//	}
}
