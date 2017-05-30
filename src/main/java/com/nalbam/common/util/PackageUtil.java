package com.nalbam.common.util;

public class PackageUtil {

    public static synchronized String getTitle(Class<?> c) {
        String v = null;
        Package p = c.getPackage();
        if (p != null) {
            v = p.getImplementationTitle();
            if (v == null) {
                v = p.getSpecificationTitle();
            }
        }
        if (v == null) {
            v = "unknown";
        }
        return v;
    }

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
