package com.handyhub.service.impl;

import com.handyhub.dto.UserDTO;
import com.handyhub.exception.dto.ResourceNotFoundException;
import com.handyhub.mapper.UserMapper;
import com.handyhub.model.AuditInfo;
import com.handyhub.model.BioData;
import com.handyhub.model.ContactInfo;
import com.handyhub.model.User;
import com.handyhub.repository.UserRepository;
import com.handyhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        user.setId(null); // Ensure it's treated as a new entity
        User userEntity =UserMapper.toEntity(user);
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
