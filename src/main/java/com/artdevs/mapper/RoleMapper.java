package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Role;
import com.artdevs.dto.user.RoleDTO;

public class RoleMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static RoleDTO convertToRoleDTO(Role role) {
        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);
        return roleDTO;
    }

    public static Role convertToRole(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        return role;
    }
}
