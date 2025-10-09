package com.handyhub.user.mapper;

import com.handyhub.user.dto.RatingDTO;
import com.handyhub.user.dto.UserDTO;
import com.handyhub.user.dto.WorkerProfileDTO;
import com.handyhub.user.model.Skill;
import com.handyhub.user.model.User;
import com.handyhub.user.model.WorkerProfile;

import java.util.Set;
import java.util.stream.Collectors;

public class WorkerProfileMapper {

    /**
     * Convert WorkerProfile Entity to DTO
     */
    public static WorkerProfileDTO toDTO(WorkerProfile entity, UserDTO userDTO) {
        if (entity == null) return null;

        WorkerProfileDTO dto = new WorkerProfileDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setExperience(entity.getExperience());
        dto.setHourlyRate(entity.getHourlyRate());
        dto.setServiceArea(entity.getServiceArea());
        dto.setAvailable(entity.isAvailable());
        dto.setEmailId(entity.getUser().getContactInfo().getEmail());
        // Map ratings
        Set<RatingDTO> ratingDTOs = entity.getRatingsReceived().stream()
                .map(RatingMapper::mapRating)
                .collect(Collectors.toSet());
        dto.setRatings(ratingDTOs);

        // Compute avg rating
        dto.setAverageRating(
                ratingDTOs.stream()
                        .mapToInt(RatingDTO::getScore)
                        .average()
                        .orElse(0.0)
        );
        dto.setSkills(entity.getSkills().stream().map(Skill::getName).collect(Collectors.toSet()));
        dto.setUserDTO(userDTO);
        if (entity.getServiceCategory() != null) {
            dto.setCategoryId(entity.getServiceCategory().getCategoryId());
        } else {
            dto.setCategoryId(null); // or default value
        }

        if (entity.getServiceSubcategory() != null) {
            dto.setSubCategoryId(entity.getServiceSubcategory().getSubcategoryId());
        } else {
            dto.setSubCategoryId(null); // or default value
        }

        return dto;
    }

    /**
     * Convert WorkerProfile DTO to Entity
     * NOTE: Only userId is set as placeholder User here.
     * You should fetch real User from DB in service layer.
     */
    public static WorkerProfile toEntity(WorkerProfileDTO dto,User user) {
        if (dto == null) return null;

        WorkerProfile entity = new WorkerProfile();
        entity.setExperience(dto.getExperience());
        entity.setHourlyRate(dto.getHourlyRate());
        entity.setServiceArea(dto.getServiceArea());
        entity.setAvailable(dto.isAvailable());
        entity.setServiceArea(dto.getServiceArea());
        entity.setUser(user);
        return entity;
    }
}
