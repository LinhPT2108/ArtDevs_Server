package com.artdevs.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artdevs.domain.entities.user.Star;
import com.artdevs.domain.entities.user.User;

public interface StarService {
	Star findById(int starId);
	Star createStar(Star star);
	Star updateStar(Star star);
	boolean deleteStar(Star star);
	Page<Star> findAllStarByUserLogged(String userLoggedEmail, Pageable pageable);
	Star findStarByUserLoggedAndMentor(User userLoggedEmail, User mentorEmail);
}
