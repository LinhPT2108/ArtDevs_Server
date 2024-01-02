package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.post.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
