package com.nalbam.common.test;

import java.util.HashSet;
import java.util.Set;

public class Intersection {

    public Set<Object> getIntersection(final Set<Object> a, final Set<Object> b) {
        final Set<Object> intersection = new HashSet<>();

        for (final Object e : a) {
            if (b.contains(e)) {
                intersection.add(e);
            }
        }

        return intersection;
    }

}
