package com.artdevs.repositories.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Role;
import com.artdevs.domain.entities.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);

	List<User> findByRole(Role role);

	Optional<User> findByEmailAndProvider(String email, String provider);
	
}
