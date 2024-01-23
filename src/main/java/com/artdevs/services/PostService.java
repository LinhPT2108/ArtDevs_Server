package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;

public interface PostService {
    Post findPostById(String postId);
    
    Optional<Post> findPostByUser(User user);

    Page<Post> findPage(int pagenumber);

    Post savePost(Post post);

    Post updatePost(Post post);

    boolean deletePost(Post post);
    
    List<Post> findAll();
    
}
