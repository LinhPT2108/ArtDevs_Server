package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;

public interface PostService {
    Post findPostById(String postId);
    
    Optional<Page<Post>> findPostByUser(User user, Pageable pageable);
    
    Optional<Page<Post>> findPostByUserAndIsDel(User user,boolean del, Pageable pageable);

    Optional<Page<Post>> findByUserAndIsDel(User user,boolean del, Pageable pageable);

    Optional<List<Post>> findPostByUserNonePage(User user);

    Optional<Page<Post>> findPostByContent(String keyword, Pageable pageable);

    Page<Post> findPage(int pagenumber);

    Post savePost(Post post);

    Post updatePost(Post post);

    boolean deletePost(Post post);
    
    List<Post> findAll();
    
    List<Post> findPostWithListFriend();
    
    Optional<List<Post>> findbyKeyword(String keyword);
    
}
