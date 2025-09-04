package com.handyhub.user.controller;

// PermissionController.java
import com.handyhub.user.dto.PermissionDTO;
import com.handyhub.user.service.PermissionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public PermissionDTO create(@RequestBody PermissionDTO dto) {
        return permissionService.createPermission(dto);
    }

    @PutMapping("/{id}")
    public PermissionDTO update(@PathVariable Long id, @RequestBody PermissionDTO dto) {
        return permissionService.updatePermission(id, dto);
    }

    @GetMapping("/{id}")
    public PermissionDTO getById(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    @GetMapping
    public List<PermissionDTO> getAll() {
        return permissionService.getAllPermissions();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        permissionService.deletePermission(id);
    }
}
