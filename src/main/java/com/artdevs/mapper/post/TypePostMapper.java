package com.artdevs.mapper.post;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.TypePost;
import com.artdevs.dto.post.TypePostDTO;

public class TypePostMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static TypePostDTO convertToTypePostDTO(TypePost typePost) {
        TypePostDTO typePostDTO = modelMapper.map(typePost, TypePostDTO.class);
        typePostDTO.setListPostOfType(getListPostOfType(typePost));
        return typePostDTO;
    }

    public static TypePost convertToTypePost(TypePostDTO typePostDTO) {
        TypePost typePost = modelMapper.map(typePostDTO, TypePost.class);
        return typePost;
    }

    private static List<Post> getListPostOfType(TypePost typePost) {
        return typePost
                .getListPostOfType().stream().map(post -> new Post(post.getPostId(), post.getImageUrl(),
                        post.getContent(), post.getTime(), post.getTimelineUserId(), post.getUser(),
                        post.getListLikePost(), post.getListSharePost(), post.getListReportPost(),
                        post.getListCommentPost(), post.getListImage(), typePost, post.getListHashtag()))
                .collect(Collectors.toList());
    }
}
