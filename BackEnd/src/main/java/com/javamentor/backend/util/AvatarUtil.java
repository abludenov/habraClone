package com.javamentor.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class AvatarUtil {

    @Value("${AvatarUtil.filePathDefaultProfileAvatar}")
    private String filePathDefaultProfileAvatar;

    public byte[] getDefaultProfileAvatar() {
        return toByteArrayFromImage(filePathDefaultProfileAvatar);
    }

    public byte[] toByteArrayFromImage(String filePath) {
        byte[] array = new byte[0];
        try {
            array = Files.readAllBytes(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
}
