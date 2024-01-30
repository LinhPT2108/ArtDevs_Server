package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Share;
import com.artdevs.domain.entities.user.User;
@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
	@Query(value = "" + "SELECT r FROM Share AS r "
			+ "WHERE r.userShareId = :user AND r.postShareId = :post ")
	Share findShareByUserIDandPostID(@Param(value = "user") User user, 
									 @Param(value = "post") Post post);
}
