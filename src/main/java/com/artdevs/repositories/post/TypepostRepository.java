package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.TypePost;
@Repository
public interface TypepostRepository extends JpaRepository<TypePost, Integer> {

}
