package com.velikokhatko.study.view.dto;

import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileDTO extends BaseEntityNamedDTO {
    private Byte[] image;
    private Double weight;
    private Double height;
    private Double lunxVolume;
    //Body Mass Index = weight/height^2
    private String bmi;
    private BaseEntityDTO performanceDTO;
    private List<BaseEntityNamedDTO> rootWorkouts;
    private List<BaseEntityNamedDTO> contests;

    @Builder
    public UserProfileDTO(Long id,
                          String name,
                          Byte[] image,
                          Double weight,
                          Double height,
                          Double lunxVolume,
                          String bmi,
                          BaseEntityDTO performanceDTO,
                          List<BaseEntityNamedDTO> rootWorkouts,
                          List<BaseEntityNamedDTO> contests) {
        super(id, name);
        this.image = image;
        this.weight = weight;
        this.height = height;
        this.lunxVolume = lunxVolume;
        this.bmi = bmi;
        this.performanceDTO = performanceDTO;
        this.rootWorkouts = rootWorkouts;
        this.contests = contests;
    }
}
