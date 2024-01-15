package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Share;
import com.artdevs.dto.post.ShareDTO;

public class ShareMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ShareDTO convertToShareDTO(Share share) {
        ShareDTO shareDTO = modelMapper.map(share, ShareDTO.class);
        shareDTO.setPostId(share.getPostShareId().getPostId());
        shareDTO.setUsername(share.getUserShareId().getUsername());
        return shareDTO;
    }

    public static Share convertToShare(ShareDTO shareDTO) {
        Share share = modelMapper.map(shareDTO, Share.class);
        return share;
    }
}
