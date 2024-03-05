package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import com.artdevs.domain.entities.post.Share;
import com.artdevs.domain.entities.user.User;

public interface ShareService {
    Share findShareById(Long shareId);

    List<Share> findAll();

	boolean addShare(String postId) throws Exception;

	boolean unShare(String postId) throws Exception;

	Optional<List<Share>> findByUser(User user);
   
}
