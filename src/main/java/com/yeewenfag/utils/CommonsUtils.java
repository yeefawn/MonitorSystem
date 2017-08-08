package com.yeewenfag.utils;

import com.yeewenfag.utils.property.PropertyUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

public class CommonsUtils {

    // 获取32位UUID
    public static String createId() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 31);
    }

    // 加密
    public static String encodePassword(String password, String salt) {
        int hashIterations = new Integer(PropertyUtils.getProperty("sys.defaultHashIterations"));
        return new Md5Hash(password, salt, hashIterations).toString();
    }
}
