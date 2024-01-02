package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.user.User;

public interface UserRepository extends JpaRepository<User, String> {

}
