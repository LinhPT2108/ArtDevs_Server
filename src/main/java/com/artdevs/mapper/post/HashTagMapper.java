package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.dto.post.HashTagDTO;

public class HashTagMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static HashTagDTO convertToHashTagDTO(HashTag hashTag) {
        HashTagDTO hashTagDTO = modelMapper.map(hashTag, HashTagDTO.class);
        hashTagDTO.setPostIdByHashTag(hashTag.getPostHashtag().getPostId());
        hashTagDTO.setHashtagDetailId(hashTag.getHashtagDetail().getId());
        return hashTagDTO;
    }

    public static HashTag convertToHashTag(HashTagDTO hashTagDTO) {
        HashTag hashTag = modelMapper.map(hashTagDTO, HashTag.class);
        return hashTag;
    }
}
