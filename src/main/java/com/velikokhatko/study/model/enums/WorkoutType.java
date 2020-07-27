package com.velikokhatko.study.model.enums;

public enum WorkoutType {
    RUN(9.1),
    BIKE(7d),
    SWIM(8.7);

    private Double caloriesPerMinute;

    public Double getCaloriesPerMinute() {
        return caloriesPerMinute;
    }

    WorkoutType(Double caloriesPerMinute) {
        this.caloriesPerMinute = caloriesPerMinute;
    }
}
