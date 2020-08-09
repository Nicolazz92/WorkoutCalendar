package com.velikokhatko.study.service;

import com.velikokhatko.study.model.Workout;
import com.velikokhatko.study.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Transactional(readOnly = true)
    public List<Workout> getRootWorkoutsByUserId(Long userProfileId) {
        return workoutRepository.findAll((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("userProfile"), userProfileId),
                        criteriaBuilder.isTrue(root.get("isRoot"))
                ));
    }
}
