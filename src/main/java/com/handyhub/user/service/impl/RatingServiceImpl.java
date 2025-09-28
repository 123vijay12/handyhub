package com.handyhub.user.service.impl;

import com.handyhub.user.dto.RatingDTO;
import com.handyhub.user.mapper.RatingMapper;
import com.handyhub.user.model.Rating;
import com.handyhub.user.repository.RatingRepository;
import com.handyhub.user.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;

    public RatingServiceImpl(RatingRepository repository) {
        this.repository = repository;
    }

    @Override
    public RatingDTO giveRating(RatingDTO dto) {
        Rating entity = RatingMapper.toEntity(dto); // you need this method in mapper
        return RatingMapper.mapRating(repository.save(entity));
    }

    @Override
    public void deleteRating(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<RatingDTO> getRatingsForWorker(Long workerId) {
        return repository.findByRatedToId(workerId).stream()
                .map(RatingMapper::mapRating)
                .collect(Collectors.toList());
    }

    @Override
    public List<RatingDTO> getRatingsByUser(Long userId) {
        return repository.findByRatedById(userId).stream()
                .map(RatingMapper::mapRating)
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageRatingForWorker(Long workerId) {
        return repository.findAverageScoreByRatedToId(workerId);
    }
}
