package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(callSuper = true, exclude = "user")
@Entity
public class Performance extends BaseEntity {
    private UserProfile user;
    private LocalTime run100m;
    private LocalTime run500m;
    private LocalTime run1000m;
    private LocalTime swim100m;
    private LocalTime swim500m;
    private LocalTime swim1000m;
    private LocalTime bike10km;
    private LocalTime bike25km;
}
