package com.velikokhatko.study.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public final class Utils {
    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("#.#");

    static {
        DecimalFormatSymbols decimalFormatSymbols = DOUBLE_FORMAT.getDecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        DOUBLE_FORMAT.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    public static String findBMI(Double weight, Double height) {
        return DOUBLE_FORMAT.format(weight / Math.pow(height / 100, 2));
    }
}
