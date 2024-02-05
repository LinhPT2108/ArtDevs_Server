package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.user.User;

@Service
public interface UserService {
	User findUserById(String userId);

	User findByEmail(String email);

	List<User> findAll();

	User saveUser(User user);

	User updateUser(User user);

	void deleteUser(User user);
	

    Optional<Page<User>> findUserByKeyword(String keyword, Pageable pageable);
    
    Optional<Page<User>> findMentorByKeyword(String keyword, Pageable pageable);

	List<User> findMentor();
	
	List<User> FindMentorIsReady();

}
