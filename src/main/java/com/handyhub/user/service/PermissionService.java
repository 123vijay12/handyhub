package com.handyhub.user.service;

import com.handyhub.user.dto.PermissionDTO;

import java.util.List;

public interface PermissionService {

    PermissionDTO createPermission(PermissionDTO permissionDTO);
    PermissionDTO updatePermission(Long id, PermissionDTO permissionDTO);
    PermissionDTO getPermissionById(Long id);
    List<PermissionDTO> getAllPermissions();
    void deletePermission(Long id);
}
