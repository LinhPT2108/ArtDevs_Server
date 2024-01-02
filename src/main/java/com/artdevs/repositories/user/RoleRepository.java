package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.user.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
