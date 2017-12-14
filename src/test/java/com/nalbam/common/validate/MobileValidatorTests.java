package com.nalbam.common.validate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class MobileValidatorTests {

    @Test
    public void mobileTest() throws Exception {
        final List<String> list = Arrays.asList(
                "0101231234",
                "01012341234",
                "010-123-1234",
                "010-1234-1234"
        );

        final MobileValidator validator = new MobileValidator();

        for (final String value : list) {
            final boolean b = validator.validate(value);

            log.debug("## mobile : {} => {}", b, value);

            Assert.assertTrue(b);
        }
    }

    @Test
    public void mobileTestFalse() throws Exception {
        final List<String> list = Arrays.asList(
                "010123123",
                "013-123-1234",
                "010-1234-12345"
        );

        final MobileValidator validator = new MobileValidator();

        for (final String value : list) {
            final boolean b = validator.validate(value);

            log.debug("## mobile : {} => {}", b, value);

            Assert.assertFalse(b);
        }
    }

}
