package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.dto.post.DetailHashtagDTO;

public class DetailHashTagMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static DetailHashtagDTO convertToDetailHashTagDTO(DetailHashtag detailHashtag) {
        DetailHashtagDTO detailHashtagDTO = modelMapper.map(detailHashtag, DetailHashtagDTO.class);
        detailHashtagDTO.setUserCreate(detailHashtag.getUserCreate().getUsername());
        detailHashtagDTO.setDescription(detailHashtag.getDescription());
        detailHashtagDTO.setCountHashtagOfDetail(detailHashtag.getListHashtagOfDetail().size());
        return detailHashtagDTO;
    }

    public static DetailHashtag convertTodDetailHashtag(DetailHashtagDTO detailHashtagDTO) {
        DetailHashtag detailHashtag = modelMapper.map(detailHashtagDTO, DetailHashtag.class);
        return detailHashtag;
    }

    private static int getListHashTag(DetailHashtag detailHashtag) {
        return detailHashtag.getListHashtagOfDetail().size();
    }
}
