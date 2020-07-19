package com.jhxaa.yhj.utli;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class PropertiesUtil {

    private static Properties prop = null;
    private static String configName = "config.properties";

    static {
        if (null == prop) {
            prop = new Properties();
            InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(configName);
            try {
                prop.load(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean getBoolean(String s) {
        String property = prop.getProperty(s);
        return Boolean.parseBoolean(property);
    }

    public static Object getObject(String propertiesName) {
        return prop.get(propertiesName);
    }


    public static String getString(String propertiesName) {
        return prop.getProperty(propertiesName);
    }

    public static Integer getIntger(String propertiesName) {
        return Integer.valueOf(prop.getProperty(propertiesName));
    }


    public static Long getLong(String propertiesName) {

        return Long.valueOf(prop.getProperty(propertiesName));
    }
}
