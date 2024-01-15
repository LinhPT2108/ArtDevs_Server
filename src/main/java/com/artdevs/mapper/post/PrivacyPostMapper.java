package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.PrivacyPost;
import com.artdevs.dto.post.privacyPostDTO;

public class PrivacyPostMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static privacyPostDTO convertToReprotDTO(PrivacyPost privacyPost) {
        privacyPostDTO privacyPostDTO = modelMapper.map(privacyPost, privacyPostDTO.class);

        return privacyPostDTO;
    }

    public static PrivacyPost convertToReport(privacyPostDTO privacyPostDTO) {
        PrivacyPost privacyPost = modelMapper.map(privacyPostDTO, PrivacyPost.class);
        return privacyPost;
    }

}
