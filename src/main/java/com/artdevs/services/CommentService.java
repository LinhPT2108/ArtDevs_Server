package com.artdevs.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.artdevs.domain.entities.post.Comment;

public interface CommentService {
    Comment findCommentById(Long commentId);

    List<Comment> findAll();

    Comment saveComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Comment comment);

	List<Comment> findCommentByPostId(String postid);

	Page<Comment> findpagecommentbyPostID(String postid, int pagenumber);
}
