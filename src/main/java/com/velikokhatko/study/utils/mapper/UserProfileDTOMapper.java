package com.velikokhatko.study.utils.mapper;

import com.velikokhatko.study.model.UserProfile;
import com.velikokhatko.study.util.Utils;
import com.velikokhatko.study.view.dto.UserProfileDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserProfileDTOMapper implements Mapper<UserProfile, UserProfileDTO> {

    @Override
    public UserProfileDTO entityToDTO(UserProfile entity) {
        return UserProfileDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .weight(entity.getWeight())
                .height(entity.getHeight())
                .lunxVolume(entity.getLunxVolume())
                .bmi(Utils.findBMI(entity.getWeight(), entity.getHeight()))
                //TODO посмотреть как это маппят
//                .performanceDTO(null)
//                .rootWorkouts(null)
//                .contests(null)
                .build();
    }

    @Override
    public BaseEntityNamedDTO entityToBaseEntityNamedDTO(UserProfile entity) {
        return new BaseEntityNamedDTO(entity.getId(), entity.getName());
    }

    @Override
    public UserProfile dtoToEntity(UserProfileDTO dto) {
        UserProfile result = new UserProfile();
        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setImage(dto.getImage());
        result.setWeight(dto.getWeight());
        result.setHeight(dto.getHeight());
        result.setLunxVolume(dto.getLunxVolume());
        //TODO посмотреть как это маппят
        return result;
    }
}
