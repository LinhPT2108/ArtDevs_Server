package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.post.HashTag;

public interface HashtagRepository extends JpaRepository<HashTag, Integer> {

}
