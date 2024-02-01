package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.PictureOfComment;

@Repository
public interface PictureOfCommentRepository extends JpaRepository<PictureOfComment, Long> {

}
