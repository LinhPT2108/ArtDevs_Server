package com.artdevs.mapper.post;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.dto.post.DetailHashtagDTO;
import com.artdevs.dto.post.HashTagDTO;

public class DetailHashTagMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static DetailHashtagDTO convertToDetailHashTagDTO(DetailHashtag detailHashtag) {
        DetailHashtagDTO detailHashtagDTO = modelMapper.map(detailHashtag, DetailHashtagDTO.class);
        detailHashtagDTO.setListHashtagOfDetail(getListHashTag(detailHashtag));
        return detailHashtagDTO;
    }

    public static DetailHashtag convertTodDetailHashtag(DetailHashtagDTO detailHashtagDTO) {
        DetailHashtag detailHashtag = modelMapper.map(detailHashtagDTO, DetailHashtag.class);
        return detailHashtag;
    }

    private static List<HashTagDTO> getListHashTag(DetailHashtag detailHashtag) {
        List<HashTagDTO> hashTagDTO = new ArrayList<>();
        List<HashTag> hashTags = detailHashtag.getListHashtagOfDetail();
        for (HashTag hashTag : hashTags) {
            hashTagDTO.add(HashTagMapper.convertToHashTagDTO(hashTag));
        }
        return hashTagDTO;
    }
}
