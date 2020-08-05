package com.velikokhatko.study.view.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
