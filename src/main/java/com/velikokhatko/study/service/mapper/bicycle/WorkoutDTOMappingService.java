package com.velikokhatko.study.service.mapper.bicycle;

import com.velikokhatko.study.model.Workout;
import com.velikokhatko.study.service.mapper.bicycle.base.BaseNamedMappingService;
import com.velikokhatko.study.view.dto.WorkoutDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WorkoutDTOMappingService extends BaseNamedMappingService<Workout, WorkoutDTO> {

    @Override
    public WorkoutDTO entityToDTO(Workout entity) {
        BaseEntityNamedDTO track = entityToBaseEntityNamedDTO(entity.getTrack());
        List<BaseEntityNamedDTO> workouts = entitiesToBaseEntityNamedDTOs(new ArrayList<>(entity.getWorkouts()));
        BaseEntityNamedDTO userProfile = entityToBaseEntityNamedDTO(entity.getUserProfile());

        return WorkoutDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .date(entity.getDate())
                .duration(entity.getDuration())
                .intensive(entity.getIntensive())
                .type(entity.getType())
                .track(track)
                .workouts(workouts)
                .userProfile(userProfile)
                .build();
    }

    @Override
    public Workout dtoToEntity(WorkoutDTO dto) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
