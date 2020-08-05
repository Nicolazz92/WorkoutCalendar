package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "userProfile")
@ToString(callSuper = true, exclude = "userProfile")
@Entity
public class Performance extends BaseEntity {

    @OneToOne(mappedBy = "performance")
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
    private LocalTime run100m;
    private LocalTime run500m;
    private LocalTime run1000m;
    private LocalTime swim100m;
    private LocalTime swim500m;
    private LocalTime swim1000m;
    private LocalTime bike10km;
    private LocalTime bike25km;
}
