package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.dto.post.ImageOfPostDTO;

public class ImageOfPostMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ImageOfPostDTO convertToImageOfPostDTO(ImageOfPost imageOfPost) {
        ImageOfPostDTO imageOfPostDTO = modelMapper.map(imageOfPost, ImageOfPostDTO.class);
        imageOfPostDTO.setPostImage(imageOfPost.getPostImage().getPostId());
        return imageOfPostDTO;
    }

    public static ImageOfPost convertToImageOfPost(ImageOfPostDTO imageOfPostDTO) {
        ImageOfPost imageOfPost = modelMapper.map(imageOfPostDTO, ImageOfPost.class);
        return imageOfPost;
    }
}
