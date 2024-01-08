package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.dto.post.DetailHashtagDTO;

public class DetailHashTagMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static DetailHashtagDTO convertToDetailHashTagDTO(DetailHashtag detailHashtag) {
        DetailHashtagDTO detailHashtagDTO = modelMapper.map(detailHashtag, DetailHashtagDTO.class);
        return detailHashtagDTO;
    }

    public static DetailHashtag convertTodDetailHashtag(DetailHashtagDTO detailHashtagDTO) {
        DetailHashtag detailHashtag = modelMapper.map(detailHashtagDTO, DetailHashtag.class);
        return detailHashtag;
    }
}
