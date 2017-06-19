package com.nalbam.common.util;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class PackageUtilTest {

    @Test
    public void getPackage() {
        Map<String, String> data = PackageUtil.getData(getClass());

        System.out.println("artifactId = " + data.get("artifactId"));
        System.out.println("version = " + data.get("version"));

        assertNotNull(data);
    }

}
