package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import com.artdevs.domain.entities.user.User;

public interface UserService {
    User findUserById(String userId);

    List<User> findAll();

    User saveUser(User user);

    User updateUser(User user);
    Optional<User> findByEmail(String user);

    void deleteUser(User user);
}
