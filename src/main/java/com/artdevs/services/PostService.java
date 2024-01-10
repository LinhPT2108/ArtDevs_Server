package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.Post;

public interface PostService {
    Post findPostById(String postId);

    List<Post> findAll();

    Post savePost(Post post);

    Post updatePost(Post post);

    boolean deletePost(Post post);
}
