package com.artdevs.services;

import com.artdevs.domain.entities.message.PictureOfMessage;

public interface PictureOfMessageService {
	PictureOfMessage savePictureOfMessage(PictureOfMessage pictureOfMessage);
	void deletePictureOfMessage(PictureOfMessage pictureOfMessage);
}
