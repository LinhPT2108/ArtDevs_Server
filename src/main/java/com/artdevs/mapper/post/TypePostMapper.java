package com.artdevs.mapper.post;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.TypePost;
import com.artdevs.dto.post.PostDTO;
import com.artdevs.dto.post.TypePostDTO;

public class TypePostMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static TypePostDTO convertToTypePostDTO(TypePost typePost) {
        TypePostDTO typePostDTO = modelMapper.map(typePost, TypePostDTO.class);
//        typePostDTO.setListPostOfType(getListPostOfType(typePost));
        return typePostDTO;
    }

    public static TypePost convertToTypePost(TypePostDTO typePostDTO) {
        TypePost typePost = modelMapper.map(typePostDTO, TypePost.class);
        return typePost;
    }

    private static List<PostDTO> getListPostOfType(TypePost typePost) {
        List<PostDTO> postDTO = new ArrayList<>();
        List<Post> posts = typePost.getListPostOfType();
        for (Post post : posts) {
            postDTO.add(PostMapper.convertoPostDTO(post));
        }
        return postDTO;
    }
}
