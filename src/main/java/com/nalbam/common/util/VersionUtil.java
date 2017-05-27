package com.nalbam.common.util;

public class VersionUtil {

    public static synchronized String getVersion(Class<?> c) {
        String v = null;
        Package p = c.getPackage();
        if (p != null) {
            v = p.getImplementationVersion();
            if (v == null) {
                v = p.getSpecificationVersion();
            }
        }
        if (v == null) {
            v = "unknown";
        }
        return v;
    }

}
