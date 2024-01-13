package com.artdevs.services.impl.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.post.PrivacyPostDetailRespository;
import com.artdevs.services.PrivacyPostDetailService;

public class PrivacyPostDetailServiceImpl implements PrivacyPostDetailService{
	@Autowired
	PrivacyPostDetailRespository privacyPostDetailRespository;
	@Override
	public List<PrivacyPostDetail> findByPost(Post post) {
		// TODO Auto-generated method stub
		return privacyPostDetailRespository.findByPost(post);
	}

	@Override
	public PrivacyPostDetail savePrivacyPostDetail(PrivacyPostDetail privacyPostDetail) {
		// TODO Auto-generated method stub
		return privacyPostDetailRespository.save(privacyPostDetail);
	}

}
