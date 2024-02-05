package com.artdevs.services.impl.post;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.repositories.post.CommentRepository;
import com.artdevs.repositories.post.PictureOfCommentRepository;
import com.artdevs.services.CloudinaryService;
import com.artdevs.services.CloudinaryValidationService;
import com.artdevs.services.CommentService;
import com.artdevs.services.ImageOfCommentService;
import com.artdevs.services.ReplyCommentService;

@Service
public class ImageOfCommentImpl implements ImageOfCommentService {
	
	@Autowired CommentRepository commentreposiroty;
	
	@Autowired CommentService commentservice;
	
	@Autowired CloudinaryService cloudinaryService;
	
	@Autowired CloudinaryValidationService cloudinaryValidation;
	
	@Autowired PictureOfCommentRepository pictureOfCommentrepository;
	
	@Autowired ReplyCommentService replycmtservice;
	
	@Override
	public PictureOfComment saveImageOfComment(long CommentId, MultipartFile file) throws Exception {
		Comment cmt = this.commentservice.findCommentById(CommentId);

		if (cmt == null) {
			return null;
		}

		String cloudinaryPublicId = UUID.randomUUID().toString();
		Map uploadMap = this.cloudinaryService.uploadImage(file, cloudinaryPublicId);

		if (!cloudinaryValidation.isValid(uploadMap)) {
			return null;
		}

	 PictureOfComment picture = new PictureOfComment();
	 	picture.setImageUrl(uploadMap.get("url").toString());
	 	picture.setPictureOfCommentId(cmt);
	 	picture.setPictureOfReplyCommentId(null);
		picture.setCloudinaryPublicId(uploadMap.get("public_id").toString());
//		picture.setImageOfPostUrl(uploadMap.get("url").toString());
//		picture.setPostImage(post);
//		picture.setTime(new Date());
		return this.pictureOfCommentrepository.save(picture);
	}
	
	@Override
	public PictureOfComment saveImageOfReplyComment(long RepCommentId, MultipartFile file) throws Exception {
		ReplyComment repcmt = replycmtservice.findByReplyCommentID(RepCommentId);

		if (repcmt == null) {
			return null;
		}

		String cloudinaryPublicId = UUID.randomUUID().toString();
		Map uploadMap = this.cloudinaryService.uploadImage(file, cloudinaryPublicId);

		if (!cloudinaryValidation.isValid(uploadMap)) {
			return null;
		}

	 PictureOfComment picture = new PictureOfComment();
	 	picture.setImageUrl(uploadMap.get("url").toString());
	 	picture.setPictureOfCommentId(null);
	 	picture.setPictureOfReplyCommentId(repcmt);
		picture.setCloudinaryPublicId(uploadMap.get("public_id").toString());
//		picture.setImageOfPostUrl(uploadMap.get("url").toString());
//		picture.setPostImage(post);
//		picture.setTime(new Date());
		return this.pictureOfCommentrepository.save(picture);
	}
}
