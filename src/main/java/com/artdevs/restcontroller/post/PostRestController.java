package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.PostDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.mapper.post.PostMapper;
import com.artdevs.services.HashTagService;
import com.artdevs.services.ImageOfPostService;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class PostRestController {

	@Autowired
	PostService postsv;

	@Autowired
	HashTagService hashtagSerivce;

	@Autowired
	UserService userservice;

	@Autowired
	ImageOfPostService imgservice;

	@GetMapping("/post/page")
	public ResponseEntity<List<PostDTO>> getPost(@RequestParam("page") int pagenumber) {
		Page<Post> page = postsv.findPage(pagenumber);
		List<PostDTO> listpost = new ArrayList<>();
		for (Post post : page) {
			listpost.add(PostMapper.convertoDTO(post, hashtagSerivce));
		}
		return ResponseEntity.ok(listpost);
	}

	@GetMapping("/postMentor/pageMentor")
	public ResponseEntity<List<PostDTO>> getPostMenter(@RequestParam("page") int pagenumber) {
		Page<Post> page = postsv.findPage(pagenumber);
		List<Post> posts = page.filter(t -> t.getUser().getRole().getRoleName().equals("mentor")).toList();
		List<PostDTO> postlist = posts.stream().map(t -> PostMapper.convertoDTO(t, hashtagSerivce))
				.collect(Collectors.toList());
		return ResponseEntity.ok(postlist);
	}

	@GetMapping("/post")
	public ResponseEntity<List<PostDTO>> getPostall() {
		List<Post> list = postsv.findAll();
		List<PostDTO> listpost = new ArrayList<>();
		for (Post post : list) {
			listpost.add(PostMapper.convertoDTO(post, hashtagSerivce));
		}
		return ResponseEntity.ok(listpost);
	}

	@PostMapping("/post")
	public ResponseEntity<Post> CreatePost(@RequestBody PostDTO postdto) {
		Post post = PostMapper.convertToPost(postdto, userservice);
		// if (postdto.getListImageofPost().size() > 0) {
		// postsv.savePost(post);
		// for (String imgURL : postdto.getListImageofPost()) {
		// ImageOfPost imgOfpost = new ImageOfPost();
		// imgOfpost.setPostImage(post);
		// imgOfpost.setImageOfPostUrl(imgURL);
		// imgservice.saveImageOfPost(imgOfpost);
		// }
		// }
		return ResponseEntity.ok(post);
	}

	@GetMapping("/mentor-of-demand")
	public ResponseEntity<?> getPostMentorOfDemand() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userservice.findByEmail(authentication.getName());
		List<User> listMentor = userservice.findAll().stream().filter(t -> t.getRole().getId() == 3).toList();
		List<UserDTO> listString = new ArrayList<>();

		List<Demand> listDemand = user.getUserDemand();
		for (Demand list : listDemand) {
			for (User m : listMentor) {
				for (Skill k : m.getUserSkill()) {
					if (k.getLanguage().getLanguageName().equals(list.getLanguage().getLanguageName())) {
						listString.add(UserMapper.UserConvertToUserDTO(m));
					}
				}
			}
		}
		return ResponseEntity.ok(listString);
	}

}
