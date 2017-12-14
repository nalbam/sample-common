package com.nalbam.common.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class PackageTests {

    @Test
    public void test() {
        final Map<String, String> data = PackageUtil.getData(this.getClass());

        log.debug("## package : {}", data);

        assertNotNull(data);
    }

}
