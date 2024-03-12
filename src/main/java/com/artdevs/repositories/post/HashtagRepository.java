package com.artdevs.repositories.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.Post;

@Repository
@EnableJpaRepositories
public interface HashtagRepository extends JpaRepository<HashTag, Integer> {
	Optional<List<HashTag>> findByDetailHashtagAndPostHashtag(DetailHashtag detailHashtag, Post postHashtag);
//	List<HashTag> findByDetailHashtagAndPostHashtag(DetailHashtag detailHashtag, Post postHashtag);
	void deleteByPostHashtag(Post postHashtag);
}
