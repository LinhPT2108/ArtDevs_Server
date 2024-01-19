package com.artdevs.restcontroller.post;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.dto.post.PostDTO;
import com.artdevs.mapper.post.PostMapper;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;

import jakarta.websocket.server.PathParam;

@RestController 
@RequestMapping(Path.path_api)
public class PostRestController {
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;

	@GetMapping("/all-post/{userId}")
	public ResponseEntity<List<PostDTO>> getMethodName(@PathVariable("userId") String userId) {
		Optional<Post> posts = postService.findPostByUser(userService.findUserById(userId));
		List<PostDTO> postDTOs = posts.stream().map(p-> new PostDTO(p.getPostId(), p.getImageUrl(), p.getContent(), 
				p.getTime(), p.getTimelineUserId())).collect(Collectors.toList());
		return ResponseEntity.ok(postDTOs);
	}

}
