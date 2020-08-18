package com.velikokhatko.study.service;

import com.velikokhatko.study.model.Workout;
import com.velikokhatko.study.repository.WorkoutRepository;
import com.velikokhatko.study.view.dto.WorkoutDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ConversionService conversionService;

    public WorkoutService(WorkoutRepository workoutRepository,
                          @Qualifier("dtoConverter") ConversionService conversionService) {
        this.workoutRepository = workoutRepository;
        this.conversionService = conversionService;
    }

    @Transactional(readOnly = true)
    public List<Workout> getRootWorkoutsByUserId(Long userProfileId) {
        return workoutRepository.findAll((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("userProfile"), userProfileId),
                        criteriaBuilder.isTrue(root.get("isRoot"))
                ));
    }

    @Transactional(readOnly = true)
    public Workout getWorkoutById(Long workoutId) {
        return workoutRepository.findById(workoutId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public WorkoutDTO getWorkoutDTOById(Long workoutId) {
        return conversionService.convert(getWorkoutById(workoutId), WorkoutDTO.class);
    }
}
