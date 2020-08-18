package com.velikokhatko.study.service.converters;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.service.converters.base.BaseEntityConverter;
import com.velikokhatko.study.service.converters.base.BaseEntityNamedConverter;
import com.velikokhatko.study.utils.Utils;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
public class UserProfileToDTOConverter implements Converter<UserProfile, UserProfileDTO> {

    private final BaseEntityConverter baseEntityConverter;
    private final BaseEntityNamedConverter baseEntityNamedConverter;

    public UserProfileToDTOConverter(BaseEntityConverter baseEntityConverter,
                                     BaseEntityNamedConverter baseEntityNamedConverter) {
        this.baseEntityConverter = baseEntityConverter;
        this.baseEntityNamedConverter = baseEntityNamedConverter;
    }

    @Override
    public UserProfileDTO convert(UserProfile entity) {
        BaseEntityDTO performanceDTO = baseEntityConverter.convert(entity.getPerformance());
        List<BaseEntityNamedDTO> contestDTOs = entity.getContests().stream()
                .map(baseEntityNamedConverter::convert)
                .collect(Collectors.toList());
        List<BaseEntityNamedDTO> workoutDTOs = entity.getWorkouts().stream()
                .map(baseEntityNamedConverter::convert)
                .collect(Collectors.toList());

        return UserProfileDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .weight(entity.getWeight())
                .height(entity.getHeight())
                .lunxVolume(entity.getLunxVolume())
                .bmi(Utils.findBMI(entity.getWeight(), entity.getHeight()))
                .performanceDTO(performanceDTO)
                .rootWorkouts(workoutDTOs)
                .contests(contestDTOs)
                .build();
    }
}
