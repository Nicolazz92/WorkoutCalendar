package com.velikokhatko.study.service;

import com.velikokhatko.study.repository.UserProfileRepository;
import com.velikokhatko.study.repository.WorkoutRepository;
import com.velikokhatko.study.utils.mapper.UserProfileDTOMapper;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final WorkoutRepository workoutRepository;
    private final UserProfileDTOMapper userProfileDTOMapper;

    public UserProfileService(UserProfileRepository userProfileRepository,
                              WorkoutRepository workoutRepository,
                              UserProfileDTOMapper userProfileDTOMapper) {
        this.userProfileRepository = userProfileRepository;
        this.workoutRepository = workoutRepository;
        this.userProfileDTOMapper = userProfileDTOMapper;
    }

    @Transactional(readOnly = true)
    public List<UserProfileDTO> getReducedUsers(String... sortByFields) {
        List<UserProfileDTO> profiles = userProfileRepository
                .findAll((root, query, criteriaBuilder) -> criteriaBuilder.and(), Sort.by(sortByFields))
                .stream().map(userProfileDTOMapper::entityToDTO)
                .collect(Collectors.toList());
        return profiles;
    }
}
