package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.domain.entities.post.Comment;
import java.util.List;



@Repository
public interface PictureOfCommentRepository extends JpaRepository<PictureOfComment, Long> {
	void deleteByPictureOfCommentId(Comment pictureOfCommentId);
	void deleteByPictureOfReplyCommentId(ReplyComment replyComment);
	PictureOfComment findByImageUrl(String ImageUrl);
}
