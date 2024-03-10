package com.artdevs.repositories.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.HashTag;
@Repository
public interface HashtagRepository extends JpaRepository<HashTag, Integer> {
	
}
