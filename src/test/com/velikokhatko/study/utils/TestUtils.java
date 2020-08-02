package com.velikokhatko.study.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

public final class TestUtils {

    public static Byte[] getBytes(String picturePath) throws IOException {
        File file = new ClassPathResource(picturePath).getFile();
        byte[] bytes = FileCopyUtils.copyToByteArray(file);
        Byte[] objectBytes = new Byte[bytes.length];
        for (int i = 0, bytesLength = bytes.length; i < bytesLength; i++) {
            objectBytes[i] = bytes[i];
        }
        return objectBytes;
    }
}
