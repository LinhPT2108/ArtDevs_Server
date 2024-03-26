package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.HashTag;
@Repository
public interface HashtagRepository extends JpaRepository<HashTag, Integer> {
	
}
