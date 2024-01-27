package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.dto.post.PostDTO;
import com.artdevs.mapper.post.PostMapper;
import com.artdevs.services.HashTagService;
import com.artdevs.services.ImageOfPostService;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class PostRestController {
	
	@Autowired PostService postsv;
	
	@Autowired HashTagService hashtagSerivce;
	
	@Autowired UserService userservice;
	
	@Autowired ImageOfPostService imgservice;
	
	@GetMapping("/post/page/{page}")
	public ResponseEntity<List<PostDTO>> getPost(@PathVariable("page") int pagenumber){
		Page<Post> page = postsv.findPage(pagenumber);
		List<PostDTO> listpost = new ArrayList<>();
		for (Post post : page) {
			listpost.add(PostMapper.convertoDTO(post,hashtagSerivce));
		}
		return ResponseEntity.ok(listpost);
	}
	@GetMapping("/post")
	public ResponseEntity<List<PostDTO>> getPostall(){
		List<Post> list = postsv.findAll();
		List<PostDTO> listpost = new ArrayList<>();
		for (Post post : list) {
			listpost.add(PostMapper.convertoDTO(post,hashtagSerivce));
		}
		return ResponseEntity.ok(listpost);
	}
	
	@PostMapping("/post")
	public ResponseEntity<Post> CreatePost(@RequestBody PostDTO postdto){
		Post post = PostMapper.convertToPost(postdto,userservice);
		if(postdto.getListImageofPost().size()>0) {
			postsv.savePost(post);
			for (String imgURL : postdto.getListImageofPost()) {
				ImageOfPost imgOfpost = new ImageOfPost();
				imgOfpost.setPostImage(post);
				imgOfpost.setImageOfPostUrl(imgURL);
				imgservice.saveImageOfPost(imgOfpost);
			}
		}
		return ResponseEntity.ok(post);
	}
}
