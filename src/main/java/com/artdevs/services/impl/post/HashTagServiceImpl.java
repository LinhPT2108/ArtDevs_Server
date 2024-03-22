package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.repositories.post.HashtagRepository;
import com.artdevs.services.HashTagService;

import jakarta.transaction.Transactional;

@Service
public class HashTagServiceImpl implements HashTagService {

	@Autowired
	HashtagRepository hashtagRepository;

	@Override
	public HashTag findHashTagById(Integer hashtagId) {
		Optional<HashTag> hashTagOptional = hashtagRepository.findById(hashtagId);
		return hashTagOptional.orElse(null);
	}

	@Override
	public List<HashTag> findAll() {
		return hashtagRepository.findAll();
	}

	@Override
	public HashTag saveHashTag(HashTag hashTag) {
		return hashtagRepository.save(hashTag);
	}

	@Override
	public HashTag updateHashTag(HashTag hashTag) {
		return hashtagRepository.save(hashTag);
	}

	@Override
	public void deleteHashTag(HashTag hashTag) {
		hashtagRepository.delete(hashTag);
	}

	@Override
	public Optional<List<HashTag>> findbydetailHashtagAndPost(DetailHashtag detailHashtag, Post post) {
		return hashtagRepository.findByDetailHashtagAndPostHashtag(detailHashtag, post);
	}

	@Transactional
	@Override
	public void deleteHashTagByPost(Post post) {
		try {
			hashtagRepository.deleteByPostHashtag(post);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
