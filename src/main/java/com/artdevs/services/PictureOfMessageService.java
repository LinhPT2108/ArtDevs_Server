package com.artdevs.services;

import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.message.PictureOfMessage;

public interface PictureOfMessageService {
	PictureOfMessage savePictureOfMessage(String messageId, MultipartFile file);
	void deletePictureOfMessage(PictureOfMessage pictureOfMessage);
}
