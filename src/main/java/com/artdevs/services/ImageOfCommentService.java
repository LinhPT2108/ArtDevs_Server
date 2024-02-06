package com.artdevs.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.PictureOfComment;

@Service
public interface ImageOfCommentService {

	PictureOfComment saveImageOfReplyComment(long RepCommentId, MultipartFile file) throws Exception;

	PictureOfComment saveImageOfComment(long CommentId, MultipartFile file) throws Exception;

}