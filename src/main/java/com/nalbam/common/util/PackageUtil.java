package com.nalbam.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class PackageUtil {

    static synchronized Map<String, String> getData(Class<?> c) {
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

}
