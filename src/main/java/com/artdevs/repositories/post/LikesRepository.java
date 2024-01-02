package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.post.Likes;

public interface LikesRepository extends JpaRepository<Likes,Long> {

}
