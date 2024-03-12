package com.artdevs.repositories.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.DetailHashtag;

@Repository
public interface DetailHashtagRepository extends JpaRepository<DetailHashtag, Integer> {

	@Query("SELECT d FROM DetailHashtag d WHERE d.hashtagText LIKE %:keyword%")
	Optional<Page<DetailHashtag>> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT d FROM DetailHashtag d WHERE d.hashtagText LIKE %:keyword%")
	Optional<Page<DetailHashtag>> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	@Query("SELECT d FROM DetailHashtag d WHERE d.hashtagText LIKE %:keyword%")
	Optional<List<DetailHashtag>> findByKeywordNonPage(@Param("keyword") String keyword);
	
	// List<DetailHashtag> findByHashtagText(String hashtagText);

	Optional<DetailHashtag> findByHashtagText(String hashtagText);
}
