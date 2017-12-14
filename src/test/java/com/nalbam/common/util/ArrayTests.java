package com.nalbam.common.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
public class ArrayTests {

    @Test
    public void test() {
        final List<Long> list1 = Arrays.asList(1L, 3L, 5L, 7L);
        final List<Long> list2 = Arrays.asList(2L, 4L, 6L, 8L);

        final List<Long> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);

        log.debug("## array : {}", list);

        assertNotNull(list);
        assertTrue(list.size() == (list1.size() + list2.size()));
    }

}
