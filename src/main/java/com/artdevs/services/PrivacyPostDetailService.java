package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.PrivacyPostDetail;

public interface PrivacyPostDetailService {
	List<PrivacyPostDetail> findByPost(Post post);
	PrivacyPostDetail savePrivacyPostDetail(PrivacyPostDetail privacyPostDetail);
	
	Optional<PrivacyPostDetail> findStatusAndPost(boolean status, Post post);
}
