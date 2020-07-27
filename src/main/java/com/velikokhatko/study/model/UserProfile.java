package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;

import java.util.List;

public class UserProfile extends BaseEntityNamed {
    private Byte[] image;
    private Double weight;
    private Double height;
    private Double lunxVolume;
    private List<Workout> workouts;
    private Performance performance;
}
