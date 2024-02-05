package com.artdevs.services.impl.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.repositories.post.CommentRepository;
import com.artdevs.repositories.post.ReplyCommentRepository;
import com.artdevs.services.ReplyCommentService;

@Service
public class ReplyCommentServiceImpl implements ReplyCommentService {

	@Autowired
	ReplyCommentRepository replyCommentRepository;
	@Autowired
	CommentRepository commentRepository;

	@Override
	public ReplyComment createReplyComment(ReplyComment replyComment) {
		// TODO Auto-generated method stub
		return replyCommentRepository.save(replyComment);
	}

	@Override
	public ReplyComment updateReplyComment(ReplyComment replyComment) {
		// TODO Auto-generated method stub
		return replyCommentRepository.save(replyComment);
	}

	@Override
	public List<ReplyComment> findReplyCommentByCommentId(Long commentId) {
		// TODO Auto-generated method stub
		return replyCommentRepository.findByCommentId(commentRepository.findById(commentId).get());
	}

	@Override
	public void deleteReplyComment(ReplyComment replyComment) {
		try {
			replyCommentRepository.delete(replyComment);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw e;
		}
	}

	@Override
	public ReplyComment findByReplyCommentID(long replyCommentID) {
		// TODO Auto-generated method stub
		
		return replyCommentRepository.findById(replyCommentID).get();
	}

}
