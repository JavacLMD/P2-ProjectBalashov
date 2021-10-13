package com.github.JavacLMD.Marketplace.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerUtil {
    private static final Logger log = LoggerFactory.getLogger(IntegerUtil.class);

    /**
     * Quick and easy parse int tool to get a value from string
     * @param value string value to be parsed
     * @param defaultValue integer value it should return if it fails (will return -9999 if null)
     * @return parsed string or default value
     */
    public static int parseInt(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            if (log.isErrorEnabled())
                log.error(e.getMessage());

            if (defaultValue != null) {
                return defaultValue;
            } else {
                return -9999;
            }
        }
    }

}
