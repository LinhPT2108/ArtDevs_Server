package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.repositories.post.CommentRepository;
import com.artdevs.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment findCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        return commentOptional.orElse(null);
    }

    @Override
    public List<Comment> findCommentByPostId(String postid) {

        return commentRepository.findByPostCommentId(postid);
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

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
