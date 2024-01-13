package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.UserService;

public class UserServiceImpl implements UserService {

    // private final UserRepository userRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
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

}
