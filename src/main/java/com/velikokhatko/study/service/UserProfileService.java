package com.velikokhatko.study.service;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.repository.UserProfileRepository;
import com.velikokhatko.study.service.converters.UserProfileToDTOConverter;
import com.velikokhatko.study.service.converters.base.BaseEntityNamedConverter;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileToDTOConverter userProfileDTOMapper;
    private final BaseEntityNamedConverter baseEntityNamedConverter;

    public UserProfileService(UserProfileRepository userProfileRepository,
                              UserProfileToDTOConverter userProfileDTOMapper,
                              BaseEntityNamedConverter baseEntityNamedConverter) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileDTOMapper = userProfileDTOMapper;
        this.baseEntityNamedConverter = baseEntityNamedConverter;
    }

    @Transactional(readOnly = true)
    public List<UserProfile> getUserProfiles(String... sortByFields) {
        return userProfileRepository.findAll((root, query, criteriaBuilder) -> criteriaBuilder.and(), Sort.by(sortByFields));
    }

    @Transactional(readOnly = true)
    public UserProfile getUserProfileById(Long userId) {
        return userProfileRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<BaseEntityNamedDTO> getUserProfileDTOs(String... sortByFields) {
        return getUserProfiles(sortByFields).stream()
                .map(baseEntityNamedConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserProfileDTO getUserProfileDTOById(Long userId) {
        return userProfileDTOMapper.convert(getUserProfileById(userId));
    }
}
