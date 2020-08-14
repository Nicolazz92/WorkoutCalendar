package com.velikokhatko.study.service;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.repository.UserProfileRepository;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final ConversionService conversionService;

    public UserProfileService(UserProfileRepository userProfileRepository,
                              @Qualifier("dtoConverter") ConversionService conversionService) {
        this.userProfileRepository = userProfileRepository;
        this.conversionService = conversionService;
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
                .map(user -> conversionService.convert(user, BaseEntityNamedDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserProfileDTO getUserProfileDTOById(Long userId) {
        return conversionService.convert(getUserProfileById(userId), UserProfileDTO.class);
    }
}
