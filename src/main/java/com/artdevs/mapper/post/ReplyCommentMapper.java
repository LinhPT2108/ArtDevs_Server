package com.artdevs.mapper.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.dto.post.ImageOfCommentDTO;
import com.artdevs.dto.post.ReplyCommentToGetDTO;
import com.artdevs.dto.post.ReplyCommentToPostDTO;
import com.artdevs.dto.post.UserPostDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.services.CommentService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

public class ReplyCommentMapper {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static ReplyComment convertToReplyComment(ReplyCommentToPostDTO replyCommentDTO, UserService userService,
			CommentService commentService) {
		ReplyComment ReplyComment = modelMapper.map(replyCommentDTO, ReplyComment.class);
		ReplyComment.setUserReportId(userService.findUserById(replyCommentDTO.getUserToPost()));
		ReplyComment.setUserReceive(userService.findUserById(replyCommentDTO.getUserReceive()));
		ReplyComment.setCommentId(commentService.findCommentById(replyCommentDTO.getCommentToPost()));
		ReplyComment.setTimeComment(new Date());
		return ReplyComment;
	}

	public static ReplyCommentToGetDTO convertToGetDTO(ReplyComment replyComment) {
		ReplyCommentToGetDTO replyCommentToGetDTO = modelMapper.map(replyComment, ReplyCommentToGetDTO.class);
		replyCommentToGetDTO.setUserID(new UserPostDTO(
			    Global.safeTrim(replyComment.getUserReportId().getUserId()),
			    Global.safeTrim(replyComment.getUserReportId().getUsername()),
			    Global.safeTrim(UserMapper.getAvatar(replyComment.getUserReportId(), true)),
			    String.join(" ", 
			    		Global.safeTrim(replyComment.getUserReportId().getFirstName()), 
			    		Global.safeTrim(replyComment.getUserReportId().getMiddleName()), 
			    		Global.safeTrim(replyComment.getUserReportId().getLastName())
			    )
			));
		
		replyCommentToGetDTO.setUserReceiveDto(new UserPostDTO(
			    Global.safeTrim(replyComment.getUserReceive().getUserId()),
			    Global.safeTrim(replyComment.getUserReceive().getUsername()),
			    Global.safeTrim(UserMapper.getAvatar(replyComment.getUserReceive(), true)),
			    String.join(" ", 
			    		Global.safeTrim(replyComment.getUserReceive().getFirstName()), 
			    		Global.safeTrim(replyComment.getUserReceive().getMiddleName()), 
			    		Global.safeTrim(replyComment.getUserReceive().getLastName())
			    )
			));
		
		replyCommentToGetDTO.setCommentID(replyComment.getCommentId().getId());
		replyCommentToGetDTO.setListPictureOfComment(getImage(replyComment));
		return replyCommentToGetDTO;
	}
	
	private static List<ImageOfCommentDTO> getImage(ReplyComment replyComment) {
		List<PictureOfComment> imageOfRepCMT = replyComment.getListPictureOfComment();
	
		List<ImageOfCommentDTO> imageOfCommentDTOs = new ArrayList<>();
		if (imageOfRepCMT !=null) {
			for (PictureOfComment i : imageOfRepCMT) {
				imageOfCommentDTOs.add(ImageOfCommentMapper.convertToImageOfRepCommentDTO(i));
			}
		}
		return imageOfCommentDTOs;
	}
	
	
}
