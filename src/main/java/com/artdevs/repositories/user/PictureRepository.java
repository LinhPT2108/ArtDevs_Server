package com.artdevs.repositories.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.domain.entities.user.User;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
	List<Picture> findByUserAndPositionOfPicture(User user, boolean position);
	List<Picture> findByUser_UserId(String user);
}
