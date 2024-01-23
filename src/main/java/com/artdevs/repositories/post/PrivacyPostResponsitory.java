package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.post.PrivacyPost;

public interface PrivacyPostResponsitory extends JpaRepository<PrivacyPost, Long> {

}
