package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.repositories.post.CommentRepository;
import com.artdevs.repositories.post.PictureOfCommentRepository;
import com.artdevs.services.CloudinaryService;
import com.artdevs.services.CommentService;
import com.artdevs.services.ImageOfCommentService;
import com.artdevs.utils.Global;

import jakarta.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    PictureOfCommentRepository pictureOfCommentRepository;
    @Override
    public Comment findCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        return commentOptional.orElse(null);
    }

    @Override
    public List<Comment> findCommentByPostId(String postid) {

        return commentRepository.findByPostCommentId(postid);
    }
    public Page<Comment> findpagecommentbyPostID(String postid,int pagenumber){
    	return commentRepository.findPageByPostCommentId(postid,PageRequest.of(pagenumber, Global.size_page,Sort.by("timeComment").descending()));
    }

    @Override
    public Page<Comment> findpagecommentbyPostID(String postid, int pagenumber) {
        return commentRepository.findPageByPostCommentId(postid, PageRequest.of(pagenumber, 2));
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }
     
    @Transactional
    @Override
    public void deleteComment(Comment comment) {
    	if(!comment.getListPictureOfComment().isEmpty()) {
    		for (PictureOfComment p : comment.getListPictureOfComment()) {
    			try {
    				pictureOfCommentRepository.delete(p);
					cloudinaryService.deleteImage(p.getCloudinaryPublicId());
				} catch (Exception e) {
					System.out.println(e);
				}				
			}
    	}
        commentRepository.delete(comment);
    }
}
