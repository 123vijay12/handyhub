package com.handyhub.mapper;


import com.handyhub.dto.UserDTO;
import com.handyhub.model.AuditInfo;
import com.handyhub.model.BioData;
import com.handyhub.model.ContactInfo;
import com.handyhub.model.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setActive(user.isActive());

        // BioData
        if (user.getBioData()!= null) {
            dto.setGender(user.getBioData().getGender());
            dto.setDateOfBirth(user.getBioData().getDateOfBirth());
            dto.setNationality(user.getBioData().getNationality());
            dto.setProfilePictureUrl(user.getBioData().getProfilePictureUrl());
        }

        // ContactInfo
        if (user.getContactInfo() != null) {
            dto.setPhone(user.getContactInfo().getPhone());
            dto.setEmail(user.getContactInfo().getEmail());
            dto.setAddress(user.getContactInfo().getAddress());
            dto.setCity(user.getContactInfo().getCity());
            dto.setState(user.getContactInfo().getState());
            dto.setCountry(user.getContactInfo().getCountry());
        }

        // AuditInfo
        if (user.getAuditInfo() != null) {
            dto.setCreatedBy(user.getAuditInfo().getCreatedBy());
            dto.setCreatedDate(user.getAuditInfo().getCreatedDate());
            dto.setUpdatedBy(user.getAuditInfo().getUpdatedBy());
            dto.setUpdatedDate(user.getAuditInfo().getUpdatedDate());
        }

        return dto;
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
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

        return user;
    }
}
