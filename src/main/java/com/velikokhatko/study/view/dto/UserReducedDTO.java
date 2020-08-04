package com.velikokhatko.study.view.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReducedDTO {
    private Long id;
    private String name;
    private Integer workouts;
}
