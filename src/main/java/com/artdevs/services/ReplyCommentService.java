package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.ReplyComment;

public interface ReplyCommentService {
	
	ReplyComment createReplyComment(ReplyComment replyComment);

	
	ReplyComment updateReplyComment(ReplyComment replyComment);
	
	List<ReplyComment> findReplyCommentByCommentId(Long commentId);
	
	void deleteReplyComment(ReplyComment replyComment);
}
