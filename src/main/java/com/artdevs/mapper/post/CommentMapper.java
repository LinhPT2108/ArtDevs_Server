package com.artdevs.mapper.post;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.dto.post.CommentDTO;

public class CommentMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

 

    public static CommentDTO convertToDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    public static Comment convertToEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }

    public static List<CommentDTO> convertListToDTO(List<Comment> comments) {
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    public static List<Comment> convertListToEntity(List<CommentDTO> commentDTOs) {
        return modelMapper.map(commentDTOs, new TypeToken<List<Comment>>() {}.getType());
    }
    
//	private static List<User> getComment(Comment comment){
//		return comment
//				.getUserReportId().stream().map(u -> new User(cmt.getId(),cmt.getContent(),cmt.getImageUrl(),cmt.getCount()
//				,cmt.getTimeComment(),cmt.getTimeComment(),cmt.getUserReportId(),post))
//				.collect(Collectors.toList());
//	}
}
