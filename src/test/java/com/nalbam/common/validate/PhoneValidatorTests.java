package com.nalbam.common.validate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class PhoneValidatorTests {

    @Test
    public void phoneTest() throws Exception {
        final List<String> list = Arrays.asList(
                "021231234",
                "0212341234",
                "03112341234",
                "02-123-1234",
                "02-1234-1234",
                "031-123-1234",
                "031-1234-1234",
                "010-123-1234",
                "010-1234-1234"
        );

        final PhoneValidator validator = new PhoneValidator();

        for (final String value : list) {
            final boolean b = validator.validate(value);

            log.debug("## phone : {} => {}", b, value);

            Assert.assertTrue(b);
        }
    }

    @Test
    public void phoneTestFalse() throws Exception {
        final List<String> list = Arrays.asList(
                "02123123",
                "031123412345",
                "02-123-123",
                "020-1234-12345"
        );

        final PhoneValidator validator = new PhoneValidator();

        for (final String value : list) {
            final boolean b = validator.validate(value);

            log.debug("## phone : {} => {}", b, value);

            Assert.assertFalse(b);
        }
    }

}
