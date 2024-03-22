package com.artdevs.dto.user;

import java.util.Date;

import com.artdevs.dto.post.UserPostDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationToGetDTO {
	private int id;
	private String message;
	private boolean isRead;
	private Date createDate = new Date();
	private UserPostDTO senderId;
	private UserPostDTO receiverId;
	private String type;
	private String postId;
	private String shareId;
}
