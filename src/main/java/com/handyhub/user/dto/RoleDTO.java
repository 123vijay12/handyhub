package com.handyhub.user.dto;

import java.util.Set;

public class RoleDTO {

    private long id;
    private String name;
    private Set<Long> permissionIds; // Store only IDs for mapping
    private Set<PermissionDTO> permissions;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Long> getPermissionIds() {
        return permissionIds;
    }

    public Set<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

    public void setPermissionIds(Set<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
