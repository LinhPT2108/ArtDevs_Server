package com.artdevs.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.ReplyComment;

@Service
public interface ImageOfCommentService {

	PictureOfComment saveImageOfReplyComment(long RepCommentId, MultipartFile file) throws Exception;

	PictureOfComment saveImageOfComment(long CommentId, MultipartFile file) throws Exception;
	
	void deleteImageOfComment(PictureOfComment pictureOfComment);
	
	void deleteImgOfCmtByCommentId(Comment comment) throws Exception;

	void deleteImgOfReplyCmtByCommentId(ReplyComment replycomment) throws Exception;
	PictureOfComment findImageUrl(String ImageUrl);
}
