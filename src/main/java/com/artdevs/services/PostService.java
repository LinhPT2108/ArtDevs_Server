package com.artdevs.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artdevs.domain.entities.post.Post;

public interface PostService {
    Post findPostById(String postId);

    Page<Post> findPage(int pagenumber);

    Post savePost(Post post);

    Post updatePost(Post post);

    boolean deletePost(Post post);
    
    List<Post> findAll();
    
}
