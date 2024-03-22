package com.artdevs.services.impl.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.repositories.post.CommentRepository;
import com.artdevs.repositories.post.PictureOfCommentRepository;
import com.artdevs.repositories.post.ReplyCommentRepository;
import com.artdevs.services.CloudinaryService;
import com.artdevs.services.ImageOfCommentService;
import com.artdevs.services.ReplyCommentService;

@Service
public class ReplyCommentServiceImpl implements ReplyCommentService {

	@Autowired
	ReplyCommentRepository replyCommentRepository;
	@Autowired
	CommentRepository commentRepository;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    PictureOfCommentRepository pictureOfCommentRepository;

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
			if(!replyComment.getListPictureOfComment().isEmpty()) {
	    		for (PictureOfComment p : replyComment.getListPictureOfComment()) {
	    			try {
	    				pictureOfCommentRepository.delete(p);
						cloudinaryService.deleteImage(p.getCloudinaryPublicId());
					} catch (Exception e) {
						System.out.println(e);
					}				
				}
	    	}
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
