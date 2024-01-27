package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.dto.post.CommentDTO;
import com.artdevs.dto.post.ReplyCommentDTO;
import com.artdevs.services.CommentService;
import com.artdevs.services.UserService;

public class ReplyCommentMapper {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static ReplyComment convertToReplyComment(ReplyCommentDTO replyCommentDTO, UserService userService,
			CommentService commentService) {
		ReplyComment ReplyComment = modelMapper.map(replyCommentDTO, ReplyComment.class);
		ReplyComment.setUserReportId(userService.findUserById(replyCommentDTO.getUserID()));
		ReplyComment.setCommentId(commentService.findCommentById(replyCommentDTO.getCommentID()));
		return ReplyComment;
	}

	public static ReplyCommentDTO convertToDTO(ReplyComment replyComment) {
		ReplyCommentDTO replyCommentDTO = modelMapper.map(replyComment, ReplyCommentDTO.class);
		replyCommentDTO.setUserID(replyComment.getUserReportId().getUserId());
		replyCommentDTO.setCommentID(replyComment.getCommentId().getId());
		return replyCommentDTO;
	}
}
