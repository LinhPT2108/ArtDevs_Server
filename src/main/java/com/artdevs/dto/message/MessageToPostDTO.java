package com.artdevs.dto.message;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageToPostDTO {
//	 private MultipartFile[] pictureOfMessages;
	 private String subject;
	 private String message;
	 private String formUserId;
	 private String toUserId;
}
