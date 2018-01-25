package com.nalbam.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

public class ConfigUtil {

    public String getVariable(final String key) {
        final String sysPropertyValue = System.getProperty(key);
        if (StringUtils.isNotEmpty(sysPropertyValue)) {
            return sysPropertyValue;
        }

        final String envPropertyValue = System.getenv(key);
        if (StringUtils.isNotEmpty(envPropertyValue)) {
            return envPropertyValue;
        }

        try {
            final Properties prop = new Properties();
            prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
            final String property = prop.getProperty(key);
            if (StringUtils.isNotEmpty(property)) {
                return property;
            }
        } catch (final Exception ignored) {
        }

        return "";
    }

}
