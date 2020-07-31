package com.velikokhatko.study;

public final class TestUtils {

    public static boolean isNotBlank(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }
}
