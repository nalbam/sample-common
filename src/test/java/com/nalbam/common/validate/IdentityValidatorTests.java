package com.nalbam.common.validate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class IdentityValidatorTests {

    @Test
    public void idTest() throws Exception {
        final List<String> list = Arrays.asList(
                "nalbam",
                "nalbam-com",
                "nalbam_427",
                "427_nalbam"
        );

        final IdentityValidator validator = new IdentityValidator();

        for (final String value : list) {
            final boolean b = validator.validate(value);

            log.debug("## id : {} => {}", b, value);

            Assert.assertTrue(b);
        }
    }

    @Test
    public void idTestFalse() throws Exception {
        final List<String> list = Arrays.asList(
                "#nalbam.com",
                "nalbam.com-"
        );

        final IdentityValidator validator = new IdentityValidator();

        for (final String value : list) {
            final boolean b = validator.validate(value);

            log.debug("## id : {} => {}", b, value);

            Assert.assertFalse(b);
        }
    }

}
