package com.handyhub.user.mapper;

import com.handyhub.user.dto.RoleDTO;
import com.handyhub.user.dto.UserDTO;
import com.handyhub.user.model.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    // Convert Entity to DTO
    public static UserDTO toDto(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        //.setPassword(user.getPassword());  // Include only if needed in UI
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setActive(user.isActive());

        // BioData
        BioData bio = user.getBioData();
        if (bio != null) {
            dto.setGender(bio.getGender());
            dto.setDateOfBirth(bio.getDateOfBirth());
            dto.setNationality(bio.getNationality());
            dto.setProfilePictureUrl(bio.getProfilePictureUrl());
        }

        // ContactInfo
        ContactInfo contact = user.getContactInfo();
        if (contact != null) {
            dto.setPhone(contact.getPhone());
            dto.setEmail(contact.getEmail());
            dto.setAddress(contact.getAddress());
            dto.setCity(contact.getCity());
            dto.setState(contact.getState());
            dto.setCountry(contact.getCountry());
        }

        // AuditInfo
        AuditInfo audit = user.getAuditInfo();
        if (audit != null) {
            dto.setCreatedBy(audit.getCreatedBy());
            dto.setCreatedDate(audit.getCreatedDate());
            dto.setUpdatedBy(audit.getUpdatedBy());
            dto.setUpdatedDate(audit.getUpdatedDate());
        }

        // Roles
        if (user.getRoles() != null) {
            Set<Long> roles = user.getRoles().stream()
                    .map(Role::getId)
                    .collect(Collectors.toSet());
            dto.setRoles(roles);

            Set<RoleDTO> rolesData = user.getRoles().stream()
                    .map(RoleMapper::toDto)
                    .collect(Collectors.toSet());

            dto.setRolesData(rolesData);
        }

        return dto;
    }

    // Convert DTO to Entity with external Role Entity Mapping
    public static User toEntity(UserDTO dto, Set<Role> roleEntities) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
       //user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setActive(dto.isActive());

        // BioData
        BioData bio = new BioData();
        bio.setGender(dto.getGender());
        bio.setDateOfBirth(dto.getDateOfBirth());
        bio.setNationality(dto.getNationality());
        bio.setProfilePictureUrl(dto.getProfilePictureUrl());
        user.setBioData(bio);

        // ContactInfo
        ContactInfo contact = new ContactInfo();
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setCountry(dto.getCountry());
        user.setContactInfo(contact);

        // AuditInfo
        AuditInfo audit = new AuditInfo();
        audit.setCreatedBy(dto.getCreatedBy());
        audit.setCreatedDate(dto.getCreatedDate());
        audit.setUpdatedBy(dto.getUpdatedBy());
        audit.setUpdatedDate(dto.getUpdatedDate());
        user.setAuditInfo(audit);

        // Roles
        if (roleEntities != null && !roleEntities.isEmpty()) {
            user.setRoles(roleEntities);
        }

        return user;
    }
}
