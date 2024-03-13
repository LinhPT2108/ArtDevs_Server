package com.artdevs.services.impl.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.post.PostRepository;
import com.artdevs.services.PostService;
import com.artdevs.services.RelationshipService;
import com.artdevs.utils.Global;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	@Autowired
	RelationshipService relationservice;

	@Override
	public Post findPostById(String postId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		return postOptional.orElse(null);
	}

	@Override
	public Page<Post> findPage(int pagenumber) {
		Page<Post> page = postRepository.findAll(PageRequest.of(pagenumber, Global.size_page));
		return page;
	}

	@Override
	public List<Post> findAll() {

		return postRepository.findAll();
	}

	@Override
	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post updatePost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public boolean deletePost(Post post) {
		post.setDel(true);
		postRepository.save(post);
		return true;
	}

	@Override
	public Optional<Page<Post>> findPostByUser(User user, Pageable pageable) {
		return postRepository.findByUser(user, pageable);
	}

	@Override
	public Optional<Page<Post>> findPostByContent(String keyword, Pageable pageable) {
		return postRepository.findbyKeyword(keyword, pageable);
	}

	@Override
	public List<Post> findPostWithListFriend() {
		// TODO Auto-generated method stub
		List<Post> Result = new ArrayList<>();

		List<User> Listfriend = relationservice.getAllFriend();

		for (User user : Listfriend) {
			for (Post u : user.getUserPost()) {
				Result.add(u);
			}
		}

		return Result;
	}

	@Override
	public Optional<List<Post>> findbyKeyword(String keyword) {
		// TODO Auto-generated method stub
		return postRepository.findbyKeywordNonePage(keyword);
	}

	@Override
	public Optional<List<Post>> findPostByUserNonePage(User user) {
		// TODO Auto-generated method stub
		return postRepository.findByUser(user);
	}

}
