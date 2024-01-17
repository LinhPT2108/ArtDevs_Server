package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.dto.post.PostDTO;
import com.artdevs.mapper.post.PostMapper;
import com.artdevs.services.PostService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class PostRestController {
	
	@Autowired PostService postsv;
	
	@GetMapping("/post")
	public ResponseEntity<List<PostDTO>> getPost(){
		List<PostDTO> listpost = new ArrayList<>();
		for (Post post : postsv.findAll()) {
			listpost.add(PostMapper.convertoPostDTO(post));
		}
		return ResponseEntity.ok(listpost);
	}
}
