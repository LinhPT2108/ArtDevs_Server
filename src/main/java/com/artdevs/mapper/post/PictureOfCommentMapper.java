package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.dto.post.PictureOfCommentDTO;

public class PictureOfCommentMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static PictureOfCommentDTO convertToPictureOfCommentDTO(PictureOfComment pictureOfComment) {
        PictureOfCommentDTO pictureOfCommentDTO = modelMapper.map(pictureOfComment, PictureOfCommentDTO.class);
        pictureOfCommentDTO.setPictureOfCommentId(pictureOfComment.getPictureOfCommentId().getId());
        pictureOfCommentDTO.setPictureOfReplyCommentId(pictureOfComment.getPictureOfReplyCommentId().getId());
        return pictureOfCommentDTO;
    }

    public static PictureOfComment convertToPictureOfComment(PictureOfCommentDTO pictureOfCommentDTO) {
        PictureOfComment pictureOfComment = modelMapper.map(pictureOfCommentDTO, PictureOfComment.class);
        return pictureOfComment;
    }
}
