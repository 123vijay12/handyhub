package com.handyhub.user.mapper;

import com.handyhub.user.dto.PermissionDTO;
import com.handyhub.user.model.Permission;

// PermissionMapper.java
public class PermissionMapper {
    public static PermissionDTO toDto(Permission entity) {
        PermissionDTO dto = new PermissionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static Permission toEntity(PermissionDTO dto) {
        Permission entity = new Permission();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}

