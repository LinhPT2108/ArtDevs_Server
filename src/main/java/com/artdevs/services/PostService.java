package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;

public interface PostService {
    Post findPostById(String postId);
    
    Optional<Post> findPostByUser(User user);

    List<Post> findAll();

    Post savePost(Post post);

    Post updatePost(Post post);

    boolean deletePost(Post post);
}
