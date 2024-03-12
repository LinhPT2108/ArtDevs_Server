package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import jakarta.transaction.Transactional;

@Service
public class ImageOfCommentImpl implements ImageOfCommentService {

	@Autowired
	CommentRepository commentreposiroty;

	@Autowired
	CommentService commentservice;

	@Autowired
	CloudinaryService cloudinaryService;

	@Autowired
	CloudinaryValidationService cloudinaryValidation;

	@Autowired
	PictureOfCommentRepository pictureOfCommentrepository;

	@Autowired
	ReplyCommentService replycmtservice;

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

	@Transactional
	@Override
	public void deleteImageOfComment(PictureOfComment pictureOfComment) {
		// TODO Auto-generated method stub
		try {
			cloudinaryService.deleteImage(pictureOfComment.getCloudinaryPublicId());
			pictureOfCommentrepository.delete(pictureOfComment);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public PictureOfComment findImageUrl(String ImageUrl) {
		try {
			PictureOfComment pictureOfComment = pictureOfCommentrepository.findByImageUrl(ImageUrl);
			return pictureOfComment;
		} catch (Exception e) {
			System.out.println("109: " + e);
			return null;
		}
	}

	@Transactional
	@Override
	public void deleteImgOfCmtByCommentId(Comment comment) throws Exception {
		List<PictureOfComment> imgs = comment.getListPictureOfComment();
		try {
			if (!imgs.isEmpty()) {
				for (PictureOfComment i : imgs) {
					cloudinaryService.deleteImage(i.getCloudinaryPublicId());
				}
			}
			pictureOfCommentrepository.deleteByPictureOfCommentId(comment);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	@Transactional
	@Override
	public void deleteImgOfReplyCmtByCommentId(ReplyComment replycomment) throws Exception {

		List<PictureOfComment> imgs = replycomment.getListPictureOfComment();
		try {
			if (!imgs.isEmpty()) {
				for (PictureOfComment i : imgs) {
					cloudinaryService.deleteImage(i.getCloudinaryPublicId());
				}
			}
			pictureOfCommentrepository.deleteByPictureOfReplyCommentId(replycomment);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
