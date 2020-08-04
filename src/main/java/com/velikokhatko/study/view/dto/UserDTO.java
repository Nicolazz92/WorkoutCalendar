package com.velikokhatko.study.view.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private Byte[] image;
    private Double weight;
    private Double height;
    private Double lunxVolume;
    //Body Mass Index = weight/height^2
    private Double bmi;
}
