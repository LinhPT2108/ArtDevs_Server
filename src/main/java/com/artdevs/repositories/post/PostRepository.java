package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.post.Post;

public interface PostRepository extends JpaRepository<Post, String> {

}
