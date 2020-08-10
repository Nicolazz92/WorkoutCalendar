package com.velikokhatko.study.service.mapper.bicycle;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.model.Workout;
import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.service.WorkoutService;
import com.velikokhatko.study.service.mapper.bicycle.base.BaseNamedMappingService;
import com.velikokhatko.study.utils.Utils;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserProfileDTOMappingService extends BaseNamedMappingService<UserProfile, UserProfileDTO> {

    private final WorkoutService workoutService;

    public UserProfileDTOMappingService(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @Override
    public UserProfileDTO entityToDTO(UserProfile entity) {
        BaseEntityDTO performanceDTO = entityToBaseEntityDTO(entity.getPerformance());
        List<BaseEntityNamedDTO> contestDTOs = entitiesToBaseEntityNamedDTOs(new ArrayList<>(entity.getContests()));
        List<Workout> rootWorkoutsByUserId = workoutService.getRootWorkoutsByUserId(entity.getId());
        List<BaseEntityNamedDTO> rootWorkoutDTOs = entitiesToBaseEntityNamedDTOs(
                rootWorkoutsByUserId.stream()
                        .map(workout -> ((BaseEntityNamed) workout))
                        .collect(Collectors.toList())
        );

        return UserProfileDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .weight(entity.getWeight())
                .height(entity.getHeight())
                .lunxVolume(entity.getLunxVolume())
                .bmi(Utils.findBMI(entity.getWeight(), entity.getHeight()))
                .performanceDTO(performanceDTO)
                .rootWorkouts(rootWorkoutDTOs)
                .contests(contestDTOs)
                .build();
    }

    @Override
    public UserProfile dtoToEntity(UserProfileDTO dto) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
