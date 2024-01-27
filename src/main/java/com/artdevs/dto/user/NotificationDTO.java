package com.artdevs.dto.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

	private int id;
	private String message;
	private boolean isRead;
	private Date createDate = new Date();
	private String senderId;
	private String receiverId;
}
