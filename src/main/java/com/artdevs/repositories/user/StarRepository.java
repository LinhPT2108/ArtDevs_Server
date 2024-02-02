package com.artdevs.repositories.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Star;
import com.artdevs.domain.entities.user.User;


@Repository
public interface StarRepository extends JpaRepository<Star, Integer>{
	Page<Star> findByUserSend_Email(String email, Pageable pageable);
	Star findByUserSendAndUserReceive(User userSend,User userReceive);
}
