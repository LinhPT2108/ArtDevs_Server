package com.artdevs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.ReplyComment;

@Service
public interface ReplyCommentService {
	
	ReplyComment createReplyComment(ReplyComment replyComment);
	
	ReplyComment findByReplyCommentID(long replyCommentID);
	
	ReplyComment updateReplyComment(ReplyComment replyComment);
	
	List<ReplyComment> findReplyCommentByCommentId(Long commentId);
	
	void deleteReplyComment(ReplyComment replyComment);
}
