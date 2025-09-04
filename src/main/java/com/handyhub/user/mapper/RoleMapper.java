package com.handyhub.user.mapper;

import com.handyhub.user.dto.RoleDTO;
import com.handyhub.user.model.Permission;
import com.handyhub.user.model.Role;

import java.util.stream.Collectors;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleDTO toDto(Role role) {
        if (role == null) return null;

        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());

        if (role.getPermissions() != null) {
            dto.setPermissions(role.getPermissions()
                    .stream()
                    .map(PermissionMapper::toDto)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static Role toEntity(RoleDTO dto) {
        if (dto == null) return null;

        Role role = new Role();
        role.setName(dto.getName());

        if (dto.getPermissions() != null) {
            Set<Permission> permissions = dto.getPermissions()
                    .stream()
                    .map(PermissionMapper::toEntity)
                    .collect(Collectors.toSet());
            role.setPermissions(permissions);
        }
        return role;
    }
}

