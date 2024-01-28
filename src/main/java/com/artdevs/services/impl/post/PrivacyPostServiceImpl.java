package com.artdevs.services.impl.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.PrivacyPost;
import com.artdevs.repositories.post.PrivacyPostResponsitory;
import com.artdevs.services.PrivacyPostService;

@Service
public class PrivacyPostServiceImpl implements PrivacyPostService{

	@Autowired
	PrivacyPostResponsitory privacyPostResponsitory;
	
	@Override
	public PrivacyPost findById(Long id) {
		// TODO Auto-generated method stub
		return privacyPostResponsitory.findById(id).get();
	}

}
