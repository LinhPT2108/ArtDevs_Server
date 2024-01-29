package com.artdevs.services.impl.post;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.post.ImageofpostRepository;
import com.artdevs.repositories.post.PostRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.CloudinaryService;
import com.artdevs.services.CloudinaryValidationService;
import com.artdevs.services.ImageOfPostService;

@Service
public class ImageOfPostServiceImpl implements ImageOfPostService {

	@Autowired
	ImageofpostRepository imageofpostRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	CloudinaryService cloudinaryService;

	@Autowired
	CloudinaryValidationService cloudinaryValidation;

	@Autowired
	PostRepository postRepository;
	
	@Override
	public ImageOfPost findImageOfPostById(Integer imageofpostId) {
		Optional<ImageOfPost> imageOfPostOptional = imageofpostRepository.findById(imageofpostId);
		return imageOfPostOptional.orElse(null);
	}

	@Override
	public List<ImageOfPost> findAll() {
		return imageofpostRepository.findAll();
	}

	@Override
	public ImageOfPost saveImageOfPost(String postId, MultipartFile file) throws Exception {
		Post post = this.postRepository.findById(postId).orElse(null);

		if (post == null) {
			return null;
		}

		String cloudinaryPublicId = UUID.randomUUID().toString();
		Map uploadMap = this.cloudinaryService.uploadImage(file, cloudinaryPublicId);

		if (!cloudinaryValidation.isValid(uploadMap)) {
			return null;
		}

		ImageOfPost picture = new ImageOfPost();
		picture.setImageOfPostUrl(uploadMap.get("url").toString());
		picture.setPostImage(post);
		picture.setTime(new Date());
		picture.setCloudinaryPublicId(uploadMap.get("public_id").toString());
		return this.imageofpostRepository.save(picture);
	}

	@Override
	public ImageOfPost updateImageOfPost(ImageOfPost imageofpost) {
		return imageofpostRepository.save(imageofpost);
	}

	@Override
	public void deleteImageOfPost(ImageOfPost imageofpost) {
		imageofpostRepository.delete(imageofpost);
	}
}
