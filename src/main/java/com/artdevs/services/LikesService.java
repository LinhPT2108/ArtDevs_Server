package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.Likes;

public interface LikesService {
    Likes findLikesById(Long likeId);

    List<Likes> findAll();

    Likes saveLikes(Likes likes);

    Likes updateLikes(Likes likes);

    void deleteLikes(Likes likes);
}
