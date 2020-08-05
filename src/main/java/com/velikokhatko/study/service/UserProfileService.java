package com.velikokhatko.study.service;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.repository.UserProfileRepository;
import com.velikokhatko.study.view.dto.UserReducedDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserReducedDTO> getReducedUsers() {
        List<UserProfile> profiles = userProfileRepository
                .findAll((root, query, criteriaBuilder) -> criteriaBuilder.and(), Sort.by("name"));
        return profiles.stream()
                .map(profile -> new UserReducedDTO(profile.getId(), profile.getName(), profile.getWorkouts().size()))
                .collect(Collectors.toList());
    }
}
