package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.dto.post.PrivacyPostDetailDTO;

public class PrivacyPostDetailMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static PrivacyPostDetailDTO convertToPrivacyPostDetailDTO(PrivacyPostDetail privacyPostDetail) {
        PrivacyPostDetailDTO privacyPostDetailDTO = modelMapper.map(privacyPostDetail, PrivacyPostDetailDTO.class);
        return privacyPostDetailDTO;
    }

    public static PrivacyPostDetail convertToPrivacyPostDetail(PrivacyPostDetailDTO privacyPostDetailDTO) {
        PrivacyPostDetail privacyPostDetail = modelMapper.map(privacyPostDetailDTO, PrivacyPostDetail.class);
        return privacyPostDetail;
    }

}
