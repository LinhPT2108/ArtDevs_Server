package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Likes;
import com.artdevs.dto.post.LikeDTO;

public class LikeMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static LikeDTO convertToLikesDTO(Likes likes) {
        LikeDTO likeDTO = modelMapper.map(likes, LikeDTO.class);
        return likeDTO;
    }

    public static Likes convertToLikes(LikeDTO likeDTO) {
        Likes likes = modelMapper.map(likeDTO, Likes.class);
        return likes;
    }
}
