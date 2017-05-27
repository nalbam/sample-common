package com.nalbam.common.util;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AddressUtilTest {

    @Test
    public void getAddress() {
        String ip = AddressUtil.getAddress();

        System.out.println("IP 는 " + ip + " 입니다.");

        assertNotNull(ip);
    }

}
