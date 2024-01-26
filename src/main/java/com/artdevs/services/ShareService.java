package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.Share;

public interface ShareService {
    Share findShareById(Long shareId);

    List<Share> findAll();

	boolean addShare(String postId) throws Exception;

	boolean unShare(String postId) throws Exception;

   
}
