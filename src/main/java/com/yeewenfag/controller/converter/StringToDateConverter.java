package com.yeewenfag.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 发
 * @create 2017-08-02 22:10
 */
public class StringToDateConverter implements Converter<String, Date>{
    @Override
    public Date convert(String source) {
        Date result = null;
        if (source != null && !source.trim().equals(""))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                result = sdf.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
