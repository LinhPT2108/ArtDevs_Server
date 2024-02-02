package com.artdevs.services.impl.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	// private final UserRepository userRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public User findUserById(String userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		System.out.println(userOptional);
		return userOptional.get();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).get();
	}

	@Override
	public Optional<Page<User>> findUserByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.searchByKeyword(keyword, pageable);
	}

	@Override
	public Optional<Page<User>> findMentorByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.searchMentorByKeyword(keyword, pageable);
	}
}
