package com.handyhub.user.service;

import com.handyhub.user.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(Long id, RoleDTO roleDTO);
    RoleDTO getRoleById(Long id);
    List<RoleDTO> getAllRoles();
    void deleteRole(Long id);
}
