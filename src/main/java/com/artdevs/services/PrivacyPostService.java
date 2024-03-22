package com.artdevs.services;

import com.artdevs.domain.entities.post.PrivacyPost;

public interface PrivacyPostService {
	PrivacyPost findById(Long id);
	PrivacyPost findByNamePrivacy(String namePrivacy);
}
