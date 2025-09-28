package com.handyhub.user.controller;

import com.handyhub.user.dto.RatingDTO;
import com.handyhub.user.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ratings")
public class RatingController {

    private final RatingService service;

    public RatingController(RatingService service) {
        this.service = service;
    }

    // Create rating
    @PostMapping
    public ResponseEntity<RatingDTO> giveRating(@RequestBody RatingDTO dto) {
        return ResponseEntity.ok(service.giveRating(dto));
    }

    // Delete rating
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        service.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    // Get ratings for worker
    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<RatingDTO>> getRatingsForWorker(@PathVariable Long workerId) {
        return ResponseEntity.ok(service.getRatingsForWorker(workerId));
    }

    // Get ratings by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getRatingsByUser(userId));
    }

    // Get average rating for worker
    @GetMapping("/worker/{workerId}/average")
    public ResponseEntity<Double> getAverageRatingForWorker(@PathVariable Long workerId) {
        return ResponseEntity.ok(service.getAverageRatingForWorker(workerId));
    }
}
