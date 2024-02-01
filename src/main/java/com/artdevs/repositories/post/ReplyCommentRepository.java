package com.artdevs.repositories.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.ReplyComment;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long> {
	List<ReplyComment> findByCommentId(Comment commentId);
}
