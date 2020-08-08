package com.velikokhatko.study.util;

public final class Utils {

    public static Double findBMI(Double weight, Double height) {
        return weight / Math.pow(height, 2);
    }
}
