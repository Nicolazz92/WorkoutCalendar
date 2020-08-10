package com.velikokhatko.study.service;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.model.base.BaseEntityNamed;
import com.velikokhatko.study.repository.UserProfileRepository;
import com.velikokhatko.study.service.mapper.bicycle.UserProfileDTOMappingService;
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
    public UserProfile getUserProfileById(Long userId) {
        return userProfileRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<BaseEntityNamedDTO> getUserProfileDTOs(String... sortByFields) {
        return userProfileDTOMapper.entitiesToBaseEntityNamedDTOs(getUserProfiles(sortByFields).stream()
                .map(entity -> (BaseEntityNamed) entity).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public BaseEntityNamedDTO getUserProfileDTOById(Long userId) {
        return userProfileDTOMapper.entityToDTO(getUserProfileById(userId));
    }
}
