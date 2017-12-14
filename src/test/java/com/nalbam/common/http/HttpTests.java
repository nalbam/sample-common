package com.nalbam.common.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

@Slf4j
public class HttpTests {

    @Test
    public void getTest() throws Exception {
        final String url = "https://member.yanolja.com/health";

        final Map map = RequestMap.get(null, url, null, null);

        log.debug("## http : {} => {}", url, map);

        Assert.assertNotNull(map);
    }

}
