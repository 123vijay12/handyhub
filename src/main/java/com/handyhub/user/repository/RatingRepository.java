package com.handyhub.user.repository;

import com.handyhub.user.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    // Find ratings received by a worker
    List<Rating> findByRatedToId(Long workerId);

    // Find ratings given by a user
    List<Rating> findByRatedById(Long userId);

    // Calculate average rating of a worker (Spring Data JPA projection)
    Double findAverageScoreByRatedToId(Long workerId);
}
