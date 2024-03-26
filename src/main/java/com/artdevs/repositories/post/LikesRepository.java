package com.artdevs.repositories.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

	@Query(value = "" + "SELECT r FROM Likes AS r "
			+ "WHERE r.userLikeId = :user AND r.postLikeId = :post ")
	Likes findLikesByUserIDandPostID(@Param(value = "user") User user, 
									 @Param(value = "post") Post post);

//	Likes findByuserLikeIdandpostLikeId(User userLikeId, Post postLikeId);

	List<Likes> findByPostLikeId(Post postLikeId);
	
	Likes findByPostLikeId_PostIdAndUserLikeId(String postId, User user);
	
}
