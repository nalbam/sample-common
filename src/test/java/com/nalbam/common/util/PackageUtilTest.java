package com.nalbam.common.util;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PackageUtilTest {

    @Test
    public void getTitle() {
        String title = PackageUtil.getTitle(getClass());

        System.out.println("Title 은 " + title + " 입니다.");

        assertNotNull(title);
    }

    @Test
    public void getVersion() {
        String version = PackageUtil.getVersion(getClass());

        System.out.println("Version 은 " + version + " 입니다.");

        assertNotNull(version);
    }

}
