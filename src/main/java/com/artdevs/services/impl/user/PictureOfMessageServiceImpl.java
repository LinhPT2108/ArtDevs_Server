package com.artdevs.services.impl.user;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.message.PictureOfMessage;
import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.repositories.message.ImageOfMessageRepository;
import com.artdevs.repositories.message.MessageRepository;
import com.artdevs.services.CloudinaryService;
import com.artdevs.services.CloudinaryValidationService;
import com.artdevs.services.PictureOfMessageService;

public class PictureOfMessageServiceImpl implements PictureOfMessageService{
	@Autowired
	private ImageOfMessageRepository imageOfMessageRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private CloudinaryValidationService cloudinaryValidation;
	
	@Override
	public PictureOfMessage savePictureOfMessage(String messageId, MultipartFile file) {
		Message message = this.messageRepository.findById(messageId).orElse(null);

		if (message == null) {
			return null;
		}

		String cloudinaryPublicId = UUID.randomUUID().toString();
		Map uploadMap;
		try {
			uploadMap = this.cloudinaryService.uploadImage(file, cloudinaryPublicId);
			if (!cloudinaryValidation.isValid(uploadMap)) {
				return null;
			}

			PictureOfMessage picture = new PictureOfMessage();
			picture.setUrl(uploadMap.get("url").toString());
			picture.setMessage(message);
			picture.setCloudinaryPublicId(uploadMap.get("public_id").toString());
			return this.imageOfMessageRepository.save(picture);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		
	}

	@Override
	public void deletePictureOfMessage(PictureOfMessage pictureOfMessage) {
		try {
			cloudinaryService.deleteImage(pictureOfMessage.getCloudinaryPublicId());
		} catch (Exception e) {
			System.out.println(e);
		}
		imageOfMessageRepository.delete(pictureOfMessage);
	}

}
