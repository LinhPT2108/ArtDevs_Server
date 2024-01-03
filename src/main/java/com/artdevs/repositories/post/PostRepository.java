package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, String> {

}
