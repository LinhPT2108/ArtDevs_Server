package com.artdevs.mapper.post;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.CommentDTO;

public class CommentMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static CommentDTO convertToCommentDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        commentDTO.setPostID(comment.getPostCommentId().getPostId());
        commentDTO.setUserID(comment.getUserReportId().getUserId());

        return commentDTO;
    }

    public static Comment convertToComment(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return comment;
    }
    
//	private static List<User> getComment(Comment comment){
//		return comment
//				.getUserReportId().stream().map(u -> new User(cmt.getId(),cmt.getContent(),cmt.getImageUrl(),cmt.getCount()
//				,cmt.getTimeComment(),cmt.getTimeComment(),cmt.getUserReportId(),post))
//				.collect(Collectors.toList());
//	}
}
