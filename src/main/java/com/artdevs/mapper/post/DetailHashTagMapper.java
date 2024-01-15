package com.artdevs.mapper.post;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.dto.post.DetailHashtagDTO;

public class DetailHashTagMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static DetailHashtagDTO convertToDetailHashTagDTO(DetailHashtag detailHashtag) {
        DetailHashtagDTO detailHashtagDTO = modelMapper.map(detailHashtag, DetailHashtagDTO.class);
        detailHashtagDTO.setListHashTag(getListHashTag(detailHashtag));
        return detailHashtagDTO;
    }

    public static DetailHashtag convertTodDetailHashtag(DetailHashtagDTO detailHashtagDTO) {
        DetailHashtag detailHashtag = modelMapper.map(detailHashtagDTO, DetailHashtag.class);
        return detailHashtag;
    }

    private static List<HashTag> getListHashTag(DetailHashtag detailHashtag) {
        return detailHashtag.getListHashtagOfDetail().stream().map(
                ht -> new HashTag(ht.getId(), ht.getCount(), ht.getPostHashtag(), ht.getHashtagDetail()))
                .collect(Collectors.toList());
    }
}
