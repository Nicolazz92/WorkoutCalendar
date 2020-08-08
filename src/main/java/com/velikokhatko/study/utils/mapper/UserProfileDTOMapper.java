package com.velikokhatko.study.utils.mapper;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserProfileDTOMapper implements Mapper<UserProfile, UserProfileDTO> {

    @Override
    public UserProfileDTO persistedToDTO(UserProfile persisted) {
        return null;
    }

    @Override
    public BaseEntityNamedDTO persistedToBaseEntityNamedDTO(UserProfile persisted) {
        return null;
    }

    @Override
    public UserProfile dtoToTransient(UserProfileDTO dto) {
        return null;
    }
}
