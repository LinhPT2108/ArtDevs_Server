package com.artdevs.repositories.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;
@Repository
public interface PostRepository extends JpaRepository<Post, String> {
	Optional<Page<Post>> findByUserAndIsDel(User user, boolean del, Pageable pageable);
	Optional<Page<Post>> findByUser(User user, Pageable pageable);
	
	@Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword%")
	Optional<Page<Post>> findbyKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword%")
	Optional<List<Post>> findbyKeywordNonePage(@Param("keyword") String keyword);
}
