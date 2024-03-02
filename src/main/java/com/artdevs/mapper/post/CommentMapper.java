package com.artdevs.mapper.post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.dto.post.CommentToGetDTO;
import com.artdevs.dto.post.CommentToPostDTO;
import com.artdevs.dto.post.ImageOfCommentDTO;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;

public class CommentMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

 

    public static CommentToPostDTO convertToCommentToPostDTO(Comment comment) {
        CommentToPostDTO CommentToPostDTO = modelMapper.map(comment, CommentToPostDTO.class);
        CommentToPostDTO.setPostToPost(comment.getPostCommentId().getPostId());
        return CommentToPostDTO;
    }
    
    public static CommentToGetDTO convertToCommentToGetDTO(Comment comment) {
    	CommentToGetDTO commenttoGetDTO = modelMapper.map(comment, CommentToGetDTO.class);
    	commenttoGetDTO.setListImageofComment(getImage(comment));
    	commenttoGetDTO.setPostID(comment.getPostCommentId().getPostId());
    	return commenttoGetDTO;
    }
    

    public static Comment convertToEntity(CommentToPostDTO commentToPostDTO,UserService userservice,PostService postservice) {
    	System.out.println(">> check comdto :"+ commentToPostDTO);
    	Comment comment = modelMapper.map(commentToPostDTO, Comment.class);

    	comment.setPostCommentId(postservice.findPostById(commentToPostDTO.getPostToPost()));
    	comment.setUserReportId(userservice.findUserById(commentToPostDTO.getUserToPost()));
        return comment;
    }

    public static List<CommentToPostDTO> convertListToDTO(List<Comment> comments) {
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentToPostDTO.class))
                .collect(Collectors.toList());
    }

    public static List<Comment> convertListToEntity(List<CommentToPostDTO> CommentToPostDTOs) {
        return modelMapper.map(CommentToPostDTOs, new TypeToken<List<Comment>>() {}.getType());
    }
    
    private static List<ImageOfCommentDTO> getImage(Comment comment) {
		List<PictureOfComment> imageOfCMT = comment.getListPictureOfComment();
		List<ImageOfCommentDTO> imageOfCommentDTOs = new ArrayList<>();
		if (imageOfCMT != null) {
			for (PictureOfComment i : imageOfCMT) {
				imageOfCommentDTOs.add(ImageOfCommentMapper.convertToImageOfCommentDTO(i));
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
