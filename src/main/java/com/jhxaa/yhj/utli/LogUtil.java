package com.jhxaa.yhj.utli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {


    public static <T> Logger getLog(Class<T> classObject) {

        Logger LOG = LoggerFactory.getLogger(classObject);
        return LOG;
    }
}
