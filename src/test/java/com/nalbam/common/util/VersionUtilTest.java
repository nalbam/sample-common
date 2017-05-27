package com.nalbam.common.util;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class VersionUtilTest {

    @Test
    public void getAddress() {
        String version = VersionUtil.getVersion(getClass());

        System.out.println("Version 은 " + version + " 입니다.");

        assertNotNull(version);
    }

}
