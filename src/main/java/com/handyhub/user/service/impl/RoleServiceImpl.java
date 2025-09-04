package com.handyhub.user.service.impl;

// RoleServiceImpl.java
import com.handyhub.user.dto.PermissionDTO;
import com.handyhub.user.dto.RoleDTO;
import com.handyhub.user.mapper.RoleMapper;
import com.handyhub.user.model.Permission;
import com.handyhub.user.model.Role;
import com.handyhub.user.repository.PermissionRepository;
import com.handyhub.user.repository.RoleRepository;
import com.handyhub.user.service.RoleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository,PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository=permissionRepository;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        // Convert role basic fields
        Role role = RoleMapper.toEntity(roleDTO);

        // Always fetch permissions from DB if only IDs are provided
        if (roleDTO.getPermissions() != null) {
            Set<Permission> permissions = roleDTO.getPermissions()
                    .stream()
                    .map(pDto -> permissionRepository.findById(pDto.getId())
                            .orElseThrow(() -> new RuntimeException("Permission not found with id: " + pDto.getId())))
                    .collect(Collectors.toSet());

            role.setPermissions(permissions);
        }

        Role savedRole = roleRepository.save(role);
        return RoleMapper.toDto(savedRole);
    }



    @Override
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        existing.setName(roleDTO.getName());
        existing.setPermissions(RoleMapper.toEntity(roleDTO).getPermissions());
        return RoleMapper.toDto(roleRepository.save(existing));
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(RoleMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
