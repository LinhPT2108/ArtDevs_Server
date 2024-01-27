package com.artdevs.mapper.post;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.CommentDTO;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;

public class CommentMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

 

    public static CommentDTO convertToCommentDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        commentDTO.setPostID(comment.getPostCommentId().getPostId());

        return commentDTO;
    }

    public static Comment convertToEntity(CommentDTO commentDTO,UserService userservice,PostService postservice) {
    	Comment comment = modelMapper.map(commentDTO, Comment.class);
    	comment.setPostCommentId(postservice.findPostById(commentDTO.getPostID()));
    	comment.setUserReportId(userservice.findUserById(commentDTO.getUserID()));
        return comment;
    }

    public static List<CommentDTO> convertListToDTO(List<Comment> comments) {
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    public static List<Comment> convertListToEntity(List<CommentDTO> commentDTOs) {
        return modelMapper.map(commentDTOs, new TypeToken<List<Comment>>() {}.getType());
    }
    
    

    // private static List<User> getComment(Comment comment){
    // return comment
    // .getUserReportId().stream().map(u -> new
    // User(cmt.getId(),cmt.getContent(),cmt.getImageUrl(),cmt.getCount()
    // ,cmt.getTimeComment(),cmt.getTimeComment(),cmt.getUserReportId(),post))
    // .collect(Collectors.toList());
    // }
}
