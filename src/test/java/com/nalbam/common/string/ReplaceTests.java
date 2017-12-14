package com.nalbam.common.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ReplaceTests {

    @Test
    public void testPhone() throws Exception {
        final List<String> list = Arrays.asList(
                "010 1111 2222",
                "010-3333-4444"
        );

        for (final String v : list) {
            final String t = v.replaceAll("[\\s-]+", "");

            log.debug("## phone : {} => {}", v, t);

            Assert.assertNotNull(t);
        }
    }

    @Test
    public void testPath() throws Exception {
        final List<String> list = Arrays.asList(
                "/users",
                "/users/100/roles",
                "/users/100/roles/20"
        );

        for (final String v : list) {
            final String t = v.replaceAll("/[0-9]+", "/*");

            log.debug("## permission : {} => {}", v, t);

            Assert.assertNotNull(t);
        }
    }

    @Test
    public void testSwagger() throws Exception {
        final List<String> list = Arrays.asList(
                "/users",
                "/users/{id}/roles",
                "/users/{user_id}/roles/{role_id}"
        );

        for (final String v : list) {
            final String t = v.replaceAll("/\\{[a-zA-Z_]+}", "/*");

            log.debug("## permission : {} => {}", v, t);

            Assert.assertNotNull(t);
        }
    }

}
