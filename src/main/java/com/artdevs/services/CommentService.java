package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.Comment;

public interface CommentService {
    Comment findCommentById(Long commentId);

    List<Comment> findAll();

    Comment saveComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Comment comment);
}
