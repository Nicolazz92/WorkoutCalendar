package com.velikokhatko.study.view.dto;

import com.velikokhatko.study.view.dto.base.BaseEntityDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class PerformanceDTO extends BaseEntityDTO {

    private UserProfileDTO userProfile;
    private LocalTime run100m;
    private LocalTime run500m;
    private LocalTime run1000m;
    private LocalTime swim100m;
    private LocalTime swim500m;
    private LocalTime swim1000m;
    private LocalTime bike10km;
    private LocalTime bike25km;

    @Builder
    public PerformanceDTO(Long id,
                          UserProfileDTO userProfile,
                          LocalTime run100m,
                          LocalTime run500m,
                          LocalTime run1000m,
                          LocalTime swim100m,
                          LocalTime swim500m,
                          LocalTime swim1000m,
                          LocalTime bike10km,
                          LocalTime bike25km) {
        super(id);
        this.userProfile = userProfile;
        this.run100m = run100m;
        this.run500m = run500m;
        this.run1000m = run1000m;
        this.swim100m = swim100m;
        this.swim500m = swim500m;
        this.swim1000m = swim1000m;
        this.bike10km = bike10km;
        this.bike25km = bike25km;
    }
}
