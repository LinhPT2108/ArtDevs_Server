package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
