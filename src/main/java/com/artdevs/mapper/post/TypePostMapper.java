package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.TypePost;
import com.artdevs.dto.post.TypePostDTO;

public class TypePostMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static TypePostDTO convertToTypePostDTO(TypePost typePost) {
        TypePostDTO typePostDTO = modelMapper.map(typePost, TypePostDTO.class);
        return typePostDTO;
    }

    public static TypePost convertToTypePost(TypePostDTO typePostDTO) {
        TypePost typePost = modelMapper.map(typePostDTO, TypePost.class);
        return typePost;
    }
}
