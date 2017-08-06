package com.yeewenfag.utils.property;

import java.util.Map;

/**
 * @author 发
 * @create 2017-08-06 22:08
 */
public class PropertyUtils {
    private static Map<String, String> propertyMap;

    public static String getProperty(String name){
        if (propertyMap != null){
            return propertyMap.get(name);
        }
        return null;
    }

    protected static void setPropertyMap(Map<String, String> map){
        propertyMap = map;
    }
}
