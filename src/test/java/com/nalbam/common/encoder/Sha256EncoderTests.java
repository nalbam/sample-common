package com.nalbam.common.encoder;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class Sha256EncoderTests {

    @Test
    public void encodeTest() throws Exception {
        final String value = Sha256Encoder.encode("string");

        log.debug("## Sha256 : {}", value);

        Assert.assertNotNull(value);
    }

}
