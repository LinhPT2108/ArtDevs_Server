package com.artdevs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Likes;

@Service
public interface LikesService {
    Likes findLikesById(Long likeId);

    List<Likes> findAll();

    Likes saveLikes(Likes likes);

    Likes updateLikes(Likes likes);

    void deleteLikes(Likes likes);

	boolean addLike(String postId) throws Exception;

	boolean unLike(String postId) throws Exception;
}
