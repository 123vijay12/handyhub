package com.handyhub.user.service.impl;
// PermissionServiceImpl.java
import com.handyhub.user.dto.PermissionDTO;
import com.handyhub.user.mapper.PermissionMapper;
import com.handyhub.user.model.Permission;
import com.handyhub.user.repository.PermissionRepository;
import com.handyhub.user.service.PermissionService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {


    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionDTO createPermission(PermissionDTO permissionDTO) {
        Permission permission = PermissionMapper.toEntity(permissionDTO);
        return PermissionMapper.toDto(permissionRepository.save(permission));
    }

    @Override
    public PermissionDTO updatePermission(Long id, PermissionDTO permissionDTO) {
        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        existing.setName(permissionDTO.getName());
        existing.setDescription(permissionDTO.getDescription());
        return PermissionMapper.toDto(permissionRepository.save(existing));
    }

    @Override
    public PermissionDTO getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .map(PermissionMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    @Override
    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(PermissionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
