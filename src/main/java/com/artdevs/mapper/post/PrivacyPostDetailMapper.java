package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.dto.post.PrivacyPostDetailDTO;
import com.artdevs.dto.post.ReportDTO;

public class PrivacyPostDetailMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    
    public static PrivacyPostDetailDTO convertToReprotDTO(PrivacyPostDetail privacyPostDetail) {
    	PrivacyPostDetailDTO privacyPostDetailDTO = modelMapper.map(privacyPostDetail, PrivacyPostDetailDTO.class);
        return privacyPostDetailDTO;
    }

    public static PrivacyPostDetail convertToReport(PrivacyPostDetailDTO privacyPostDetailDTO) {
    	PrivacyPostDetail privacyPostDetail = modelMapper.map(privacyPostDetailDTO, PrivacyPostDetail.class);
        return privacyPostDetail;
    }

}
