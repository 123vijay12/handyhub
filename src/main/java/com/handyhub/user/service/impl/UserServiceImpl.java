package com.handyhub.user.service.impl;

import com.handyhub.user.dto.UserDTO;
import com.handyhub.shared.exception.custom.ResourceNotFoundException;
import com.handyhub.user.mapper.UserMapper;
import com.handyhub.user.model.*;
import com.handyhub.user.repository.RoleRepository;
import com.handyhub.user.repository.UserRepository;
import com.handyhub.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        // Convert role IDs from DTO into Role entities
        Set<Role> roles = user.getRoles().stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(() -> new ResourceNotFoundException("role", "id", roleId)))
                .collect(Collectors.toSet());
        User userEntity = UserMapper.toEntity(user, roles);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Update allowed fields
        existingUser.setUsername(dto.getUsername());
        existingUser.setFirstName(dto.getFirstName());
        existingUser.setLastName(dto.getLastName());
        existingUser.setActive(dto.isActive());

        // Update roles if provided
        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            Set<Role> roles = dto.getRoles().stream()
                    .map(roleId -> roleRepository.findById(roleId)
                            .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId)))
                    .collect(Collectors.toSet());
            existingUser.setRoles(roles);
        }

        // BioData
        if (existingUser.getBioData() == null) {
            existingUser.setBioData(new BioData());
        }
        BioData bio = existingUser.getBioData();
        bio.setGender(dto.getGender());
        bio.setDateOfBirth(dto.getDateOfBirth());
        bio.setNationality(dto.getNationality());
        bio.setProfilePictureUrl(dto.getProfilePictureUrl());

        // ContactInfo
        if (existingUser.getContactInfo() == null) {
            existingUser.setContactInfo(new ContactInfo());
        }
        ContactInfo contact = existingUser.getContactInfo();
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setCountry(dto.getCountry());

        // AuditInfo — only update `updatedBy` and `updatedDate`
        if (existingUser.getAuditInfo() == null) {
            existingUser.setAuditInfo(new AuditInfo());
        }
        AuditInfo audit = existingUser.getAuditInfo();
        audit.setUpdatedBy(dto.getUpdatedBy());
        audit.setUpdatedDate(dto.getUpdatedDate());

        // Save and return updated DTO
        User savedUser = userRepository.save(existingUser);
        return UserMapper.toDto(savedUser);
    }


    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }
}
