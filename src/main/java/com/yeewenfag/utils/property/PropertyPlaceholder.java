package com.yeewenfag.utils.property;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 重写PropertyPlaceholderConfigurer实现在代码中动态获取properties文件中的值
 * @author 发
 * @create 2017-08-06 22:05
 */
public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        Map<String, String> propertyMap = new HashMap<>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
        PropertyUtils.setPropertyMap(propertyMap);
    }
}
