package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.ImageOfPost;
import java.util.List;

@Repository
public interface ImageofpostRepository extends JpaRepository<ImageOfPost, Integer> {
	ImageOfPost findByImageOfPostUrl(String imageOfPostUrl);
}
