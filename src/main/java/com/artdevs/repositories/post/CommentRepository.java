package com.artdevs.repositories.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Query("SELECT p FROM Comment p WHERE p.postCommentId.postId = :postId")
	List<Comment> findByPostCommentId(String postId);
	
	@Query("SELECT p FROM Comment p WHERE p.postCommentId.postId = :postId")
	Page<Comment> findPageByPostCommentId(String postId,Pageable pageable);
}
