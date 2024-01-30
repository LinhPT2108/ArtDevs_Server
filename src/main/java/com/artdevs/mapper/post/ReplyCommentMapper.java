package com.artdevs.mapper.post;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.dto.post.ImageOfCommentDTO;
import com.artdevs.dto.post.ReplyCommentToGetDTO;
import com.artdevs.dto.post.ReplyCommentToPostDTO;
import com.artdevs.services.CommentService;
import com.artdevs.services.UserService;

public class ReplyCommentMapper {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static ReplyComment convertToReplyComment(ReplyCommentToPostDTO replyCommentDTO, UserService userService,
			CommentService commentService) {
		ReplyComment ReplyComment = modelMapper.map(replyCommentDTO, ReplyComment.class);
		ReplyComment.setUserReportId(userService.findUserById(replyCommentDTO.getUserID()));
		ReplyComment.setCommentId(commentService.findCommentById(replyCommentDTO.getCommentID()));
		return ReplyComment;
	}

	public static ReplyCommentToGetDTO convertToGetDTO(ReplyComment replyComment) {
		ReplyCommentToGetDTO replyCommentToGetDTO = modelMapper.map(replyComment, ReplyCommentToGetDTO.class);
		replyCommentToGetDTO.setUserID(replyComment.getUserReportId().getUserId());
		replyCommentToGetDTO.setCommentID(replyComment.getCommentId().getId());
		replyCommentToGetDTO.setListPictureOfComment(getImage(replyComment));
		return replyCommentToGetDTO;
	}
	
	private static List<ImageOfCommentDTO> getImage(ReplyComment replyComment) {
		List<PictureOfComment> imageOfRepCMT = replyComment.getListPictureOfComment();
	
		List<ImageOfCommentDTO> imageOfCommentDTOs = new ArrayList<>();
		if (imageOfCommentDTOs !=null) {
			for (PictureOfComment i : imageOfRepCMT) {
				imageOfCommentDTOs.add(ImageOfCommentMapper.convertToImageOfRepCommentDTO(i));
			}
		}
		return imageOfCommentDTOs;
	}
	
	
}
