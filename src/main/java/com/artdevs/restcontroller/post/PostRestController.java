package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.PostDTO;
import com.artdevs.dto.post.PostToGetDTO;
import com.artdevs.mapper.post.PostMapper;
import com.artdevs.services.DetailHashTagService;
import com.artdevs.services.HashTagService;
import com.artdevs.services.ImageOfPostService;
import com.artdevs.services.PostService;
import com.artdevs.services.PrivacyPostDetailService;
import com.artdevs.services.PrivacyPostService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(Global.path_api)
public class PostRestController {

	@Autowired
	PostService postsv;

	@Autowired
	HashTagService hashtagSerivce;

	@Autowired
	DetailHashTagService detailHashTagService;

	@Autowired
	UserService userservice;

	@Autowired
	ImageOfPostService imgservice;

	@Autowired
	PrivacyPostDetailService privacyPostDetailService;

	@Autowired
	PrivacyPostService privacyPostService;

	@GetMapping("/post/page")
	public ResponseEntity<List<PostToGetDTO>> getPost(@RequestParam("page") int pagenumber) {
		Page<Post> page = postsv.findPage(pagenumber);
		List<PostToGetDTO> listpost = new ArrayList<>();
		for (Post post : page) {
			listpost.add(PostMapper.convertoGetDTO(post, hashtagSerivce));
		}
		return ResponseEntity.ok(listpost);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostToGetDTO> getPostById(@PathVariable("postId") String postId) {
		Post post = postsv.findPostById(postId);

		return ResponseEntity.ok(PostMapper.convertoGetDTO(post, hashtagSerivce));
	}

	@GetMapping("/post")
	public ResponseEntity<?> getPostall(@RequestParam("page") Optional<Integer> p) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authenticate.getName());
		if (!authenticate.getName().equals("anonymousUser")) {
			String loggedInUserEmail = authenticate.getName();
			User user = userservice.findByEmail(loggedInUserEmail);
			Pageable pageable = PageRequest.of(p.orElse(0), 7, Sort.by("time").descending());
			Optional<Page<Post>> list = postsv.findPostByUser(user, pageable);

			System.out.println(">>>check list: "+ list.get());
			List<PostToGetDTO> listpost = new ArrayList<>();
			for (Post post : list.get()) {
				listpost.add(PostMapper.convertoGetDTO(post, hashtagSerivce));
			}
			return ResponseEntity.ok(listpost); 
		} else {
			return ResponseEntity.ok(HttpStatus.SC_UNAUTHORIZED);
		}
	}

	@PostMapping("/post")
	public ResponseEntity<PostToGetDTO> CreatePost(@ModelAttribute PostDTO postdto) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserEmail = authenticate.getName();
		User user = userservice.findByEmail(loggedInUserEmail);
		Post post = PostMapper.convertToPost(postdto, userservice);
		post.setUser(user);
		post.setTime(new Date());
		post.setTimelineUserId(new Date());
		Post postsave = postsv.savePost(post);

		PrivacyPostDetail privacyPost = new PrivacyPostDetail();
		privacyPost.setPost(postsave);
		privacyPost.setStatus(true);
		privacyPost.setCreateDate(new Date());
		privacyPost.setPrivacyPost(privacyPostService.findById(postdto.getPrivacyPostDetails()));
		privacyPostDetailService.savePrivacyPostDetail(privacyPost);
		List<PrivacyPostDetail> privacyPostDetails = new ArrayList<>();
		privacyPostDetails.add(privacyPost);
		postsave.setPrivacyPostDetails(privacyPostDetails);
		System.out.println(">>> lenght:" + postdto.getListImageofPost().length);
		if (postdto.getListImageofPost().length > 0) {
			List<ImageOfPost> imageOfPosts = new ArrayList<>();
			MultipartFile[] listImg = postdto.getListImageofPost();
			for (MultipartFile m : listImg) {
				try {
					ImageOfPost imageOfPost = imgservice.saveImageOfPost(postsave.getPostId(), m);
					imageOfPosts.add(imageOfPost);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			postsave.setListImage(imageOfPosts);
		}

		if (postdto.getListHashtag() != null) {
			List<HashTag> hashTags = new ArrayList<>();
			for (Integer h : postdto.getListHashtag()) {
				DetailHashtag detailHashtag = detailHashTagService.findDetailHashtagById(h);
				HashTag hashtagSave = new HashTag();
				hashtagSave.setHashtagDetail(detailHashtag);
				hashtagSave.setPostHashtag(postsave);
				hashtagSerivce.saveHashTag(hashtagSave);
				hashTags.add(hashtagSave);
			}

			postsave.setListHashtag(hashTags);
		}
		return ResponseEntity.ok(PostMapper.convertoGetDTO(postsave, hashtagSerivce));
	}

	@PutMapping("path/{id}")
	public ResponseEntity<?> putMethodName(@PathVariable("id") String id) {
		// TODO: process PUT request

		return ResponseEntity.ok().build();
	}
}
