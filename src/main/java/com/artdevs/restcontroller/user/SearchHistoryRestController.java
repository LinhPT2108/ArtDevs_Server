package com.artdevs.restcontroller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.SearchHistory;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.DetailHashtagDTO;
import com.artdevs.dto.post.PostToGetDTO;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.mapper.post.DetailHashTagMapper;
import com.artdevs.mapper.post.PostMapper;
import com.artdevs.services.DetailHashTagService;
import com.artdevs.services.HashTagService;
import com.artdevs.services.PostService;
import com.artdevs.services.SearchHistoryService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api + "/search")
public class SearchHistoryRestController {
	@Autowired
	UserService userService;

	@Autowired
	SearchHistoryService searchHistoryService;

	@Autowired
	PostService postService;

	@Autowired
	HashTagService hashTagService;

	@Autowired
	DetailHashTagService detailHashTagService;

	@GetMapping("/post")
	public ResponseEntity<?> getPostByKeyword(@RequestParam String keyword, @RequestParam("page") Optional<Integer> p) {
		searchHistoryService.deleteDulicateSearchHistory(keyword);
		List<Post> posts = new ArrayList<>();

		String[] listKeyword = keyword.split(" ");

		Pageable pageable = PageRequest.of(p.orElse(0), Global.size_page, Sort.by("time").descending());
		for (String s : listKeyword) {
			System.out.println(s);
			Optional<Page<Post>> postMatchKeyWord = postService.findPostByContent(s, pageable);
			if (postMatchKeyWord.isPresent()) {
				posts.addAll(postMatchKeyWord.get().toList());
			}
		}

		SearchHistory SearchHistorySave = searchHistoryService.saveSearchHistory(keyword);
		List<PostToGetDTO> postToGetDTOs = new ArrayList<>();
		for (Post post : posts) {
			postToGetDTOs.add(PostMapper.convertoGetDTO(post, hashTagService));
		}
		if (SearchHistorySave != null) {
			return ResponseEntity.ok(postToGetDTOs);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/people")
	public ResponseEntity<?> getPeopleByKeyword(@RequestParam String keyword,
			@RequestParam("page") Optional<Integer> p) {
		searchHistoryService.deleteDulicateSearchHistory(keyword);
		List<User> users = new ArrayList<>();

		String[] listKeyword = keyword.split(" ");

		Pageable pageable = PageRequest.of(p.orElse(0), Global.size_page);
		for (String s : listKeyword) {
			System.out.println(s);
			Optional<Page<User>> userMatchKeyWord = userService.findUserByKeyword(s, pageable);
			if (userMatchKeyWord.isPresent()) {
				users.addAll(userMatchKeyWord.get().toList());
			}
		}

		SearchHistory SearchHistorySave = searchHistoryService.saveSearchHistory(keyword);
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User u : users) {
			userDTOs.add(UserMapper.UserConvertToUserDTO(u));
		}
		if (SearchHistorySave != null) {
			return ResponseEntity.ok(userDTOs);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/mentor")
	public ResponseEntity<?> getMentorByKeyword(@RequestParam String keyword,
			@RequestParam("page") Optional<Integer> p) {
		searchHistoryService.deleteDulicateSearchHistory(keyword);
		List<User> users = new ArrayList<>();

		String[] listKeyword = keyword.split(" ");

		Pageable pageable = PageRequest.of(p.orElse(0), Global.size_page);
		for (String s : listKeyword) {
			System.out.println(s);
			Optional<Page<User>> userMatchKeyWord = userService.findMentorByKeyword(s, pageable);
			if (userMatchKeyWord.isPresent()) {
				users.addAll(userMatchKeyWord.get().toList());
			}
		}

		SearchHistory SearchHistorySave = searchHistoryService.saveSearchHistory(keyword);
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User u : users) {
			userDTOs.add(UserMapper.UserConvertToUserDTO(u));
		}
		if (SearchHistorySave != null) {
			return ResponseEntity.ok(userDTOs);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/hashtag")
	public ResponseEntity<?> getMethodName(@RequestParam String keyword, @RequestParam("page") Optional<Integer> p) {
		searchHistoryService.deleteDulicateSearchHistory(keyword);
		Pageable pageable = PageRequest.of(p.orElse(0), Global.size_page);
		Optional<Page<DetailHashtag>> detailHashtags = detailHashTagService.findbyKeyword(keyword, pageable);
		List<DetailHashtagDTO> detailHashtagDTOs = new ArrayList<>();
		if (detailHashtags.isPresent()) {
			for (DetailHashtag d : detailHashtags.get()) {
				detailHashtagDTOs.add(DetailHashTagMapper.convertToDetailHashTagDTO(d));
			}
		}
		return ResponseEntity.ok(detailHashtagDTOs);

	}

}
