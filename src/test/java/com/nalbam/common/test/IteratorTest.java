package com.nalbam.common.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class IteratorTest {

    @Test
    public void testList() {
        final String[] testList = new String[5];

        testList[0] = "a";
        testList[1] = "b";
        testList[3] = "c";
        testList[4] = "d";

        final Iterator iterator = new DealIterator(testList);

        while (iterator.hashNext()) {
            System.out.print(iterator.next() + "\n");
        }
    }

    @Test
    public void testIntersection() {
        final Set<Object> a = new HashSet<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");

        final Set<Object> b = new HashSet<>();
        b.add("c");
        b.add("d");
        b.add("e");
        b.add("f");

        final Intersection intersection = new Intersection();

        final Set<Object> c = intersection.getIntersection(a, b);

        log.info(" {} {} ", c, "reerererer");
    }

}
