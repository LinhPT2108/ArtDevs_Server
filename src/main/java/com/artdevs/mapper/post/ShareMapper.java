package com.artdevs.mapper.post;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Share;
import com.artdevs.dto.post.ShareDTO;
import com.artdevs.dto.post.UserPostDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.services.HashTagService;
import com.artdevs.services.LikesService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

public class ShareMapper {
	private static final ModelMapper modelMapper = new ModelMapper();

	public static ShareDTO convertToShareDTO(Share share, HashTagService hashtagSerivce, UserService userService,
			LikesService likesService) {
		ShareDTO shareDTO = modelMapper.map(share, ShareDTO.class);
		shareDTO.setPostId(PostMapper.convertoGetDTO(share.getPostShareId(), hashtagSerivce, userService, likesService));
		shareDTO.setUserPostDto(
				new UserPostDTO(
					    Global.safeTrim(share.getUserShareId().getUserId()),
					    Global.safeTrim(share.getUserShareId().getUsername()),
					    Global.safeTrim(UserMapper.getAvatar(share.getUserShareId(), true)),
					    String.join(" ", 
					    		Global.safeTrim(share.getUserShareId().getFirstName()), 
					    		Global.safeTrim(share.getUserShareId().getMiddleName()), 
					    		Global.safeTrim(share.getUserShareId().getLastName())
					    )
					)
				);
		shareDTO.setTypePost("share");
		return shareDTO;
	}

	public static Share convertToShare(ShareDTO shareDTO) {
		Share share = modelMapper.map(shareDTO, Share.class);
		return share;
	}
}
