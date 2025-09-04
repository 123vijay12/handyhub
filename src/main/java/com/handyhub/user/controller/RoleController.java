package com.handyhub.user.controller;

// RoleController.java
import com.handyhub.user.dto.RoleDTO;
import com.handyhub.user.service.RoleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public RoleDTO create(@RequestBody RoleDTO dto) {
        return roleService.createRole(dto);
    }

    @PutMapping("/{id}")
    public RoleDTO update(@PathVariable Long id, @RequestBody RoleDTO dto) {
        return roleService.updateRole(id, dto);
    }

    @GetMapping("/{id}")
    public RoleDTO getById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping
    public List<RoleDTO> getAll() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
