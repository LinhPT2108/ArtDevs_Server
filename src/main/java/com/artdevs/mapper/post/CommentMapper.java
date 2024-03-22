package com.artdevs.mapper.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.dto.post.CommentToGetDTO;
import com.artdevs.dto.post.CommentToPostDTO;
import com.artdevs.dto.post.ImageOfCommentDTO;
import com.artdevs.dto.post.UserPostDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

public class CommentMapper {
	private static final ModelMapper modelMapper = new ModelMapper();

	public static CommentToPostDTO convertToCommentToPostDTO(Comment comment) {
		CommentToPostDTO CommentToPostDTO = modelMapper.map(comment, CommentToPostDTO.class);
		CommentToPostDTO.setPostToPost(comment.getPostCommentId().getPostId());
		return CommentToPostDTO;
	}

	public static CommentToGetDTO convertToCommentToGetDTO(Comment comment) {
		CommentToGetDTO commenttoGetDTO = modelMapper.map(comment, CommentToGetDTO.class);
		commenttoGetDTO.setUserID(new UserPostDTO(
			    Global.safeTrim(comment.getUserReportId().getUserId()),
			    Global.safeTrim(comment.getUserReportId().getUsername()),
			    Global.safeTrim(UserMapper.getAvatar(comment.getUserReportId(), true)),
			    String.join(" ", 
			    		Global.safeTrim(comment.getUserReportId().getFirstName()), 
			    		Global.safeTrim(comment.getUserReportId().getMiddleName()), 
			    		Global.safeTrim(comment.getUserReportId().getLastName())
			    )
			));
		commenttoGetDTO.setUserReceiveDto(new UserPostDTO(
			    Global.safeTrim(comment.getUserReceive().getUserId()),
			    Global.safeTrim(comment.getUserReceive().getUsername()),
			    Global.safeTrim(UserMapper.getAvatar(comment.getUserReceive(), true)),
			    String.join(" ", 
			    		Global.safeTrim(comment.getUserReceive().getFirstName()), 
			    		Global.safeTrim(comment.getUserReceive().getMiddleName()), 
			    		Global.safeTrim(comment.getUserReceive().getLastName())
			    )
			));
		commenttoGetDTO.setListImageofComment(getImage(comment));
		commenttoGetDTO.setListReplyComment(comment.getListReplyCommentPost()!=null?comment.getListReplyCommentPost().stream()
				.map(t -> ReplyCommentMapper.convertToGetDTO(t)).collect(Collectors.toList()):null);
		return commenttoGetDTO;
	}

	public static Comment convertToEntity(CommentToPostDTO CommentToPostDTO, UserService userservice,
			PostService postservice) {
		Comment comment = modelMapper.map(CommentToPostDTO, Comment.class);
		comment.setPostCommentId(postservice.findPostById(CommentToPostDTO.getPostToPost()));
		comment.setUserReportId(userservice.findUserById(CommentToPostDTO.getUserToPost()));
		comment.setTimeComment(new Date());
//		comment.setId(Long.parseLong("123213"));
		return comment;
	}

	public static List<CommentToPostDTO> convertListToDTO(List<Comment> comments) {
		return comments.stream().map(comment -> modelMapper.map(comment, CommentToPostDTO.class))
				.collect(Collectors.toList());
	}

	public static List<Comment> convertListToEntity(List<CommentToPostDTO> CommentToPostDTOs) {
		return modelMapper.map(CommentToPostDTOs, new TypeToken<List<Comment>>() {
		}.getType());
	}

	private static List<String> getImage(Comment comment) {
		List<PictureOfComment> imageOfCMT = comment.getListPictureOfComment();
		List<String> imageOfCommentDTOs = new ArrayList<>();
		if (imageOfCMT != null) {
			for (PictureOfComment i : imageOfCMT) {
				imageOfCommentDTOs.add(ImageOfCommentMapper.convertToImageOfCommentDTO(i).getImageOfCommentUrl());
			}
		}
		return imageOfCommentDTOs;
	}

	// private static List<User> getComment(Comment comment){
	// return comment
	// .getUserReportId().stream().map(u -> new
	// User(cmt.getId(),cmt.getContent(),cmt.getImageUrl(),cmt.getCount()
	// ,cmt.getTimeComment(),cmt.getTimeComment(),cmt.getUserReportId(),post))
	// .collect(Collectors.toList());
	// }
}
