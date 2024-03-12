package com.artdevs.repositories.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.PrivacyPostDetail;

@Repository
public interface PrivacyPostDetailRespository extends JpaRepository<PrivacyPostDetail, Long>{
	List<PrivacyPostDetail> findByPost(Post post);
	Optional<PrivacyPostDetail> findByStatusAndPost(boolean status, Post post);
}
