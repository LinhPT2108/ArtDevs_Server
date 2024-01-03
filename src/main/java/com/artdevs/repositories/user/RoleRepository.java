package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
