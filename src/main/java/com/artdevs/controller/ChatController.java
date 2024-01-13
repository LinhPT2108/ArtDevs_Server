package com.artdevs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.artdevs.dto.message.MessageDTO;

@Controller
public class ChatController {
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/private-message")
	public MessageDTO recMessage(@Payload MessageDTO message) {
		simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
		System.out.println(message.toString());
		return message;
	}
}
