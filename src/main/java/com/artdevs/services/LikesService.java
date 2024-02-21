package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;

@Service
public interface LikesService {
    Likes findLikesById(Long likeId);

    List<Likes> findAll();

    Likes saveLikes(Likes likes);

    Likes updateLikes(Likes likes);

    void deleteLikes(Likes likes);

	boolean addLike(String postId) throws Exception;

	boolean unLike(String postId) throws Exception;
	
	Likes findByPostAndUserLogged(String postId, User user);
	
}
