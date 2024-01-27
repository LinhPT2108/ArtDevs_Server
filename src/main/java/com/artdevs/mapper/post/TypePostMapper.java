package com.artdevs.mapper.post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.TypePost;
import com.artdevs.dto.post.PostDTO;
import com.artdevs.dto.post.TypePostDTO;
import com.artdevs.services.HashTagService;

public class TypePostMapper {
	private static final ModelMapper modelMapper = new ModelMapper();

	public static TypePostDTO convertToTypePostDTO(TypePost typePost, HashTagService hashTagService) {
		TypePostDTO typePostDTO = modelMapper.map(typePost, TypePostDTO.class);
		typePostDTO.setListPostOfType(getListPostOfType(typePost,hashTagService));
		return typePostDTO;
	}

	public static TypePost convertToTypePost(TypePostDTO typePostDTO) {
		TypePost typePost = modelMapper.map(typePostDTO, TypePost.class);
		return typePost;
	}

	private static List<PostDTO> getListPostOfType(TypePost typePost, HashTagService hashTagService) {
		List<Post> posts = typePost.getListPostOfType();
		List<PostDTO> postDTOs = new ArrayList<>();
		for (Post post : posts) {
			postDTOs.add(PostMapper.convertoDTO(post, hashTagService));
		}
		return postDTOs;
	}
}
