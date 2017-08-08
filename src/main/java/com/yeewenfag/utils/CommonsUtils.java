package com.yeewenfag.utils;

import java.util.UUID;

public class CommonsUtils {

    // 获取32位UUID
    public static String createId() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 31);
    }
}
