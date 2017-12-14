package com.nalbam.common.validate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class NumericValidatorTests {

    @Test
    public void numericTest() throws Exception {
        final List<Long> list = Arrays.asList(
                0L,
                3L,
                56L
        );

        final NumericValidator validator = new NumericValidator();

        for (final Object value : list) {
            final boolean b = validator.validate(value);

            log.debug("## numeric : {} => {}", b, value);

            Assert.assertTrue(b);
        }
    }

    @Test
    public void numericTestObject() throws Exception {
        final List<Object> list = Arrays.asList(
                "0",
                "3",
                "67"
        );

        final NumericValidator validator = new NumericValidator();

        for (final Object value : list) {
            final boolean b = validator.validate(value);

            log.debug("## numeric : {} => {}", b, value);

            Assert.assertTrue(b);
        }
    }

    @Test
    public void numericTestFalse() throws Exception {
        final List<String> list = Arrays.asList(
                "x",
                "021-231-7723",
                "test"
        );

        final NumericValidator validator = new NumericValidator();

        for (final Object value : list) {
            final boolean b = validator.validate(value);

            log.debug("## numeric : {} => {}", b, value);

            Assert.assertFalse(b);
        }
    }

}
