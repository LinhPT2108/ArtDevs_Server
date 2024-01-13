package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.domain.entities.user.User;

public interface PrivacyPostDetailService {
	List<PrivacyPostDetail> findByPost(Post post);
	PrivacyPostDetail savePrivacyPostDetail(PrivacyPostDetail privacyPostDetail);
}
