package com.handyhub.user.service;

import com.handyhub.user.dto.RatingDTO;

import java.util.List;

public interface RatingService {
    RatingDTO giveRating(RatingDTO dto);
    void deleteRating(Long id);
    List<RatingDTO> getRatingsForWorker(Long workerId);
    List<RatingDTO> getRatingsByUser(Long userId);
    Double getAverageRatingForWorker(Long workerId);

}
