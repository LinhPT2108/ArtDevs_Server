package com.artdevs.services.impl.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.message.PictureOfMessage;
import com.artdevs.repositories.message.ImageOfMessageRepository;
import com.artdevs.services.PictureOfMessageService;

public class PictureOfMessageServiceImpl implements PictureOfMessageService{
	@Autowired
	private ImageOfMessageRepository imageOfMessageRepository;
	@Override
	public PictureOfMessage savePictureOfMessage(PictureOfMessage pictureOfMessage) {
		// TODO Auto-generated method stub
		return imageOfMessageRepository.save(pictureOfMessage);
	}

	@Override
	public void deletePictureOfMessage(PictureOfMessage pictureOfMessage) {
		// TODO Auto-generated method stub
		imageOfMessageRepository.delete(pictureOfMessage);
	}

}
