package com.nalbam.common.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

@Slf4j
public class RandomStringTests {

    @Test
    public void uuidTest() throws Exception {
        final String value = UUID.randomUUID().toString();

        log.debug("## randomUUID : {}", value);

        Assert.assertNotNull(value);
    }

}
