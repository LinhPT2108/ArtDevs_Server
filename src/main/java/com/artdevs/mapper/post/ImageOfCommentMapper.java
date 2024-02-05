package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.dto.post.ImageOfCommentDTO;
import com.artdevs.dto.post.ImageOfPostDTO;


public class ImageOfCommentMapper {
	private static final ModelMapper modelMapper = new ModelMapper();

    public static ImageOfCommentDTO convertToImageOfCommentDTO(PictureOfComment pictureofcomment) {
    	ImageOfCommentDTO imageOfCommentDTO = modelMapper.map(pictureofcomment, ImageOfCommentDTO.class);
    	
    	imageOfCommentDTO.setCommentID(pictureofcomment.getPictureOfCommentId().getId());
        return imageOfCommentDTO;
    }
    
    public static ImageOfCommentDTO convertToImageOfRepCommentDTO(PictureOfComment pictureofcomment) {
    	ImageOfCommentDTO imageOfCommentDTO = modelMapper.map(pictureofcomment, ImageOfCommentDTO.class);
    	
    	imageOfCommentDTO.setRepCommentID(pictureofcomment.getPictureOfReplyCommentId().getId());
        return imageOfCommentDTO;
    }

    public static ImageOfPost convertToImageOfPost(ImageOfPostDTO imageOfPostDTO) {
        ImageOfPost imageOfPost = modelMapper.map(imageOfPostDTO, ImageOfPost.class);
        return imageOfPost;
    }
}
