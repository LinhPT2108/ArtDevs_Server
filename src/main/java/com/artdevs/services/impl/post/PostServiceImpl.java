package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.repositories.post.PostRepository;
import com.artdevs.services.PostService;
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post findPostById(String postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.orElse(null);
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

}