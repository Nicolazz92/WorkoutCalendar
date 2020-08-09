package com.velikokhatko.study.service;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.repository.UserProfileRepository;
import com.velikokhatko.study.service.mapper.UserProfileDTOMappingService;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileDTOMappingService userProfileDTOMapper;

    public UserProfileService(UserProfileRepository userProfileRepository,
                              UserProfileDTOMappingService userProfileDTOMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileDTOMapper = userProfileDTOMapper;
    }

    @Transactional(readOnly = true)
    public List<UserProfile> getUserProfiles(String... sortByFields) {
        return userProfileRepository.findAll((root, query, criteriaBuilder) -> criteriaBuilder.and(), Sort.by(sortByFields));
    }

    @Transactional(readOnly = true)
    public List<UserProfileDTO> getUserProfileDTOs(String... sortByFields) {
        return userProfileDTOMapper.entitiesToDTOs(getUserProfiles(sortByFields));
    }
}
