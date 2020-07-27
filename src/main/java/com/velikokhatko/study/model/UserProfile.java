package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"workouts"}, callSuper = true)
@ToString(callSuper = true, exclude = {"image", "workouts", "performance"})
@Entity
public class UserProfile extends BaseEntityNamed {
    private Byte[] image;
    private Double weight;
    private Double height;
    private Double lunxVolume;
    private List<Workout> workouts;
    private Performance performance;
}
