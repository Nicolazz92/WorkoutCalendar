package com.velikokhatko.study.utils;

public final class Utils {

    public static Double findBMI(Double weight, Double height) {
        return weight / Math.pow(height, 2);
    }
}
