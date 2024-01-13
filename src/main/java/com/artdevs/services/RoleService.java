package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.user.Role;

public interface RoleService {
    Role findRoleById(Integer roleId);

    List<Role> findAll();

    Role saveRole(Role role);

    Role updateRole(Role role);

    void deleteRole(Role role);
}
