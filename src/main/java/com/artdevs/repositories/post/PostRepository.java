package com.artdevs.repositories.post;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;
@Repository
public interface PostRepository extends JpaRepository<Post, String> {
	Optional<Post> findByUser(User user);
}
