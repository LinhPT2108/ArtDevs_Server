package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Share;
import com.artdevs.dto.post.PostToGetDTO;
import com.artdevs.dto.post.ShareDTO;
import com.artdevs.services.HashTagService;
import com.artdevs.services.LikesService;
import com.artdevs.services.UserService;

public class ShareMapper {
	private static final ModelMapper modelMapper = new ModelMapper();

	public static ShareDTO convertToShareDTO(Share share, HashTagService hashtagSerivce, UserService userService,
			LikesService likesService) {
		ShareDTO shareDTO = modelMapper.map(share, ShareDTO.class);
		shareDTO.setPostId(PostMapper.convertoGetDTO(share.getPostShareId(), hashtagSerivce, userService, likesService));
		shareDTO.setFullname(share.getUserShareId().getFirstName()+" "+share.getUserShareId().getMiddleName()+" "+share.getUserShareId().getLastName() );
		shareDTO.setTypePost("share");
		return shareDTO;
	}

	public static Share convertToShare(ShareDTO shareDTO) {
		Share share = modelMapper.map(shareDTO, Share.class);
		return share;
	}
	
	public static ShareDTO convertToShareDTOByPost(PostToGetDTO post, HashTagService hashtagSerivce, UserService userService,
			LikesService likesService) {
		ShareDTO shareDTO = new ShareDTO();
		shareDTO.setPostId(post);
		shareDTO.setFullname("");
		shareDTO.setTypePost("");
		shareDTO.setContent("");
		return shareDTO;
	}
}
