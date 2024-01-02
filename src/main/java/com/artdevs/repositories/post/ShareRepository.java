package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.post.Share;

public interface ShareRepository extends JpaRepository<Share, Long> {

}
