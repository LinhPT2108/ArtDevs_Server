package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.SearchHistory;
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
import com.artdevs.services.RelationshipService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
@CrossOrigin("*")
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

	@Autowired
	RelationshipService relationshipService;

	@GetMapping("/post/page")
	public ResponseEntity<List<PostToGetDTO>> getPost(@RequestParam("page") int pagenumber) {
		Page<Post> page = postsv.findPage(pagenumber);
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		User userLogged = userservice.findByEmail(authenticate.getName());
		List<Post> posts = postsv.findAll().stream()
				.filter(post -> !post.isDel() && post.getUser().getUserId() != userLogged.getUserId())
				.collect(Collectors.toList());

		System.out.println(posts.size());
		List<PostToGetDTO> listpost = new ArrayList<>();
		for (Post post : page) {
			listpost.add(PostMapper.convertoGetDTO(post, hashtagSerivce));
		}
		return ResponseEntity.ok(listpost);
	}

	@GetMapping(value = "/friend-posts")
	public ResponseEntity<?> getFriendPosts(@RequestParam("page") Optional<Integer> p) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (!authenticate.getName().equals("anonymousUser")) {
			List<User> listFriend = relationshipService.getAllFriend();
			List<Post> listPostFriend = new ArrayList<>();
			for (User u : listFriend) {
				if (!u.getUserPost().isEmpty()) {
					listPostFriend.addAll(u.getUserPost());
				}
			}

			int pageSize = Global.size_page;
			int currentPage = p.orElse(0);

			int start = currentPage * pageSize;
			int end = Math.min((start + pageSize), listPostFriend.size());

			List<Post> sublist = listPostFriend.subList(start, end);

			Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("time").descending());
			Page<Post> postPage = new PageImpl<>(sublist, pageable, listPostFriend.size());

			return ResponseEntity.ok(postPage.get()
					.filter(t -> !t.isDel() && t.getPrivacyPostDetails().stream()
							.anyMatch(d -> d.isStatus() && d.getPrivacyPost().getId() == 1))
					.map(t -> PostMapper.convertoGetDTO(t, hashtagSerivce)));

		} else {
			return ResponseEntity.ok(HttpStatus.SC_UNAUTHORIZED);
		}
	}

	@GetMapping("/mentor-posts")
	public ResponseEntity<?> getMentorPosts(@RequestParam("page") Optional<Integer> p) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (!authenticate.getName().equals("anonymousUser")) {
			List<User> listMentor = userservice.findMentor();
			List<Post> listPostMentor = new ArrayList<>();
			for (User u : listMentor) {
				if (!u.getUserPost().isEmpty()) {
					listPostMentor.addAll(u.getUserPost());
				}
			}

			int pageSize = Global.size_page;
			int currentPage = p.orElse(0);

			int start = currentPage * pageSize;
			int end = Math.min((start + pageSize), listPostMentor.size());

			List<Post> sublist = listPostMentor.subList(start, end);

			Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("time").descending());
			Page<Post> postPage = new PageImpl<>(sublist, pageable, listPostMentor.size());

			return ResponseEntity.ok(postPage.get()
					.filter(t -> !t.isDel() && t.getPrivacyPostDetails().stream()
							.anyMatch(d -> d.isStatus() && d.getPrivacyPost().getId() == 1))
					.map(t -> PostMapper.convertoGetDTO(t, hashtagSerivce)));
		} else {
			return ResponseEntity.ok(HttpStatus.SC_UNAUTHORIZED);
		}
	}

	@GetMapping("/news-feed")
	public ResponseEntity<?> getMethodName(@RequestParam("page") Optional<Integer> p) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (!authenticate.getName().equals("anonymousUser")) {
			User userLogged = userservice.findByEmail(authenticate.getName());
			List<Post> listPostNewsFeed = new ArrayList<>();

			List<Demand> demandsUser = userLogged.getUserDemand();
			List<Post> posts = postsv.findAll().stream()
					.filter(post -> !post.isDel() && post.getUser().getUserId() != userLogged.getUserId()
							&& post.getPrivacyPostDetails().stream()
									.anyMatch(detail -> detail.isStatus() && detail.getPrivacyPost().getId() == 1))
					.collect(Collectors.toList());

			for (Demand d : demandsUser) {
				Optional<List<Post>> postMatchDemand = postsv.findbyKeyword(d.getLanguage().getLanguageName());
				if (postMatchDemand.isPresent()) {
					System.out.println(">>demand: " + postMatchDemand.isPresent());
					listPostNewsFeed.addAll(postMatchDemand.get());
				}
			}
			for (SearchHistory s : userLogged.getUserSearchHistory()) {
				Optional<List<Post>> postMatchSearchHistory = postsv.findbyKeyword(s.getKeyword());
				if (postMatchSearchHistory.isPresent()) {
					listPostNewsFeed.addAll(postMatchSearchHistory.get());
				}
			}

			int pageSize = Global.size_page;
			int currentPage = p.orElse(0);

			int start = currentPage * pageSize;
			int end = Math.min((start + pageSize), listPostNewsFeed.size());

			List<Post> sublist = listPostNewsFeed.subList(start, end);

			Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("time").descending());
			Page<Post> postPage = new PageImpl<>(sublist, pageable, listPostNewsFeed.size());

			return ResponseEntity.ok(postPage.get()
					.filter(t -> !t.isDel() && t.getPrivacyPostDetails().stream()
							.anyMatch(d -> d.isStatus() && d.getPrivacyPost().getId() == 1))
					.distinct().map(t -> PostMapper.convertoGetDTO(t, hashtagSerivce)));
		} else {
			return ResponseEntity.ok(HttpStatus.SC_UNAUTHORIZED);
		}
	}

	@GetMapping("/post-with-id")
	public ResponseEntity<PostToGetDTO> getPostById(@RequestParam("postId") String postId) {
		Post post = postsv.findPostById(postId);
		return ResponseEntity.ok(PostMapper.convertoGetDTO(post, hashtagSerivce));
	}

	@GetMapping("/post")
	public ResponseEntity<?> getPostAll() {
		return ResponseEntity.ok(postsv.findAll());
	}

	@GetMapping("/post-by-user-logged")
	public ResponseEntity<?> getPostUserLogged(@RequestParam("page") Optional<Integer> p) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authenticate.getName());
		if (!authenticate.getName().equals("anonymousUser")) {
			String loggedInUserEmail = authenticate.getName();
			User user = userservice.findByEmail(loggedInUserEmail);
			System.out.println(">>> check user: " + user.getUserId());
			Pageable pageable = PageRequest.of(p.orElse(0), 7, Sort.by("time").descending());
			Optional<Page<Post>> list = postsv.findByUserAndIsDel(user, false, pageable);
			List<PostToGetDTO> listpost = new ArrayList<>();
			for (Post post : list.get()) {
				listpost.add(PostMapper.convertoGetDTO(post, hashtagSerivce));
			}
			return ResponseEntity.ok(listpost);
		} else {
			return ResponseEntity.ok(HttpStatus.SC_UNAUTHORIZED);
		}
	}
	
	@GetMapping("/post-by-mentor-logged/{mentorid}")
	public ResponseEntity<?> getPostUserLogged(@PathVariable("mentorid") String mentorid,@RequestParam("page") Optional<Integer> p) {
		User user = userservice.findUserById(mentorid);
		if (user != null) {
			Pageable pageable = PageRequest.of(p.orElse(0), 7, Sort.by("time").descending());
			Optional<Page<Post>> list = postsv.findPostByUser(user, pageable);
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
	public ResponseEntity<PostToGetDTO> CreatePost(@RequestBody PostDTO postdto) {
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

//		if (postdto.getListImageofPost() != null) {
//			List<ImageOfPost> imageOfPosts = new ArrayList<>();
//			MultipartFile[] listImg = postdto.getListImageofPost();
//			for (MultipartFile m : listImg) {
//				try {
//					ImageOfPost imageOfPost = imgservice.saveImageOfPost(postsave.getPostId(), m);
//					imageOfPosts.add(imageOfPost);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					System.out.println(e);
//					e.printStackTrace();
//				}
//			}
//			postsave.setListImage(imageOfPosts);
//		}

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

	@PostMapping("/post-new")
	public ResponseEntity<PostToGetDTO> CreateNewPost(@RequestBody PostDTO postdto) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserEmail = authenticate.getName();
		User user = userservice.findByEmail(loggedInUserEmail);
		Post post = PostMapper.convertToPost(postdto, userservice);
		post.setUser(user);
		post.setTime(new Date());
		post.setTimelineUserId(new Date());
		Post postsave = postsv.savePost(post);
		System.out.println(">> check post: " + post.getContent());

		PrivacyPostDetail privacyPost = new PrivacyPostDetail();
		privacyPost.setPost(postsave);
		privacyPost.setStatus(true);
		privacyPost.setCreateDate(new Date());
		privacyPost.setPrivacyPost(privacyPostService.findById(postdto.getPrivacyPostDetails()));
		privacyPostDetailService.savePrivacyPostDetail(privacyPost);
		List<PrivacyPostDetail> privacyPostDetails = new ArrayList<>();
		privacyPostDetails.add(privacyPost);
		postsave.setPrivacyPostDetails(privacyPostDetails);

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

	@PutMapping("/post/{id}/hidden")
	public ResponseEntity<?> putMethodName(@PathVariable("id") String id) {
		Post post = postsv.findPostById(id);
		post.setDel(true);
		return ResponseEntity.ok(postsv.savePost(post));
	}

}
