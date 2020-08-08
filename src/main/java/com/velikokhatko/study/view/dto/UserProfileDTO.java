package com.velikokhatko.study.view.dto;

import com.velikokhatko.study.view.dto.base.BaseEntityNamedDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProfileDTO extends BaseEntityNamedDTO {
    private Byte[] image;
    private Double weight;
    private Double height;
    private Double lunxVolume;
    //Body Mass Index = weight/height^2
    private Double bmi;
    private List<WorkoutDTO> rootWorkouts;

    @Builder
    public UserProfileDTO(Long id,
                          String name,
                          Byte[] image,
                          Double weight,
                          Double height,
                          Double lunxVolume,
                          Double bmi,
                          List<WorkoutDTO> rootWorkouts) {
        super(id, name);
        this.image = image;
        this.weight = weight;
        this.height = height;
        this.lunxVolume = lunxVolume;
        this.bmi = bmi;
        this.rootWorkouts = rootWorkouts;
    }
}
