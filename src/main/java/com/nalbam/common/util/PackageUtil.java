package com.nalbam.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PackageUtil {

    public static synchronized Map<String, String> getData(Class<?> c) {
        Map<String, String> data = new ConcurrentHashMap<>();
        Package p = c.getPackage();
        if (p != null) {
            if (p.getImplementationTitle() != null) {
                data.put("artifactId", p.getImplementationTitle());
            } else if (p.getSpecificationTitle() != null) {
                data.put("artifactId", p.getSpecificationTitle());
            }
            if (p.getImplementationVersion() != null) {
                data.put("version", p.getImplementationVersion());
            } else if (p.getSpecificationVersion() != null) {
                data.put("version", p.getSpecificationVersion());
            }
        }
        data.put("address", AddressUtil.getAddress());
        return data;
    }

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
