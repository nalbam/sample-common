package com.nalbam.common.validate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class EmailValidatorTests {

    @Test
    public void emailTest() throws Exception {
        final List<String> list = Arrays.asList(
                "ya@yanolja.com",
                "ya-4.2_7@yanolja.com"
        );

        final EmailValidator validator = new EmailValidator();

        for (final String value : list) {
            final boolean b = validator.validate(value);

            log.debug("## email : {} => {}", b, value);

            Assert.assertTrue(b);
        }
    }

    @Test
    public void emailTestFalse() throws Exception {
        final List<String> list = Arrays.asList(
                "ya@yanolja@com",
                "ya-4.27_yanolja.com"
        );

        final EmailValidator validator = new EmailValidator();

        for (final String value : list) {
            final boolean b = validator.validate(value);

            log.debug("## email : {} => {}", b, value);

            Assert.assertFalse(b);
        }
    }

}
