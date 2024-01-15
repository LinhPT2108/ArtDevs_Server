package com.artdevs.mapper.post;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.PrivacyPost;
import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.dto.post.PrivacyPostDetailDTO;
import com.artdevs.dto.post.privacyPostDTO;

public class PrivacyPostMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static privacyPostDTO convertToPrivacyPostDTO(PrivacyPost privacyPost) {
        privacyPostDTO privacyPostDTO = modelMapper.map(privacyPost, privacyPostDTO.class);
        privacyPostDTO.setListPrivacyPostDetails(getPrivacyPostDetail(privacyPost));
        return privacyPostDTO;
    }

    public static PrivacyPost convertToPrivacyPost(privacyPostDTO privacyPostDTO) {
        PrivacyPost privacyPost = modelMapper.map(privacyPostDTO, PrivacyPost.class);
        return privacyPost;
    }

    public static List<PrivacyPostDetailDTO> getPrivacyPostDetail(PrivacyPost privacyPost) {
        List<PrivacyPostDetailDTO> privacyPostDetailDTO = new ArrayList<>();
        List<PrivacyPostDetail> privacyPostDetails = privacyPost.getPrivacyPostDetails();
        for (PrivacyPostDetail privacyPostDetail : privacyPostDetails) {
            privacyPostDetailDTO.add(PrivacyPostDetailMapper.convertToPrivacyPostDetailDTO(privacyPostDetail));
        }

        return privacyPostDetailDTO;
    }
}
