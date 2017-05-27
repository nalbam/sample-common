package com.nalbam.common.util;

/**
 * Created by nalbam on 2017. 5. 27..
 */
public class VersionUtil {

    public static synchronized String getVersion(Class<?> c) {
        String version = null;
        Package p = c.getPackage();
        if (p != null) {
            version = p.getImplementationVersion();
            if (version == null) {
                version = p.getSpecificationVersion();
            }
        }
        if (version == null) {
            version = "";
        }
        return version;
    }

}
