package com.handyhub.user.mapper;

import com.handyhub.user.dto.RatingDTO;
import com.handyhub.user.model.Rating;
import com.handyhub.user.model.User;
import com.handyhub.user.model.WorkerProfile;

public class RatingMapper {

    /**
     * Convert Rating Entity to DTO
     */
    public static RatingDTO mapRating(Rating rating) {
        if (rating == null) return null;

        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setRatedById(rating.getRatedBy() != null ? rating.getRatedBy().getId() : null);
        dto.setRatedToId(rating.getRatedTo() != null ? rating.getRatedTo().getId() : null);
        dto.setScore(rating.getScore());
        dto.setComment(rating.getComment());
        return dto;
    }

    /**
     * Convert DTO to Rating Entity
     * NOTE: Only IDs are mapped to User placeholders here.
     * You should fetch full User entities in service layer if needed.
     */
    public static Rating toEntity(RatingDTO dto) {
        if (dto == null) return null;

        Rating rating = new Rating();
        rating.setId(dto.getId());
        rating.setScore(dto.getScore());
        rating.setComment(dto.getComment());

        if (dto.getRatedById() != null) {
            User ratedBy = new User();
            ratedBy.setId(dto.getRatedById());
            rating.setRatedBy(ratedBy);
        }

        if (dto.getRatedToId() != null) {
            WorkerProfile ratedTo = new WorkerProfile();
            ratedTo.setId(dto.getRatedToId());
            rating.setRatedTo(ratedTo);
        }

        return rating;
    }
}
