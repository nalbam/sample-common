package com.nalbam.common.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;

public class TimeAgoTest {

    @Test
    public void testAgo() {
        Calendar c = Calendar.getInstance();
        c.set(2007, Calendar.APRIL, 28);

        String ago = TimeAgo.ago(c);

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(f.format(c.getTime()) + " 은 " + ago + " 입니다.");

        assertNotNull(ago);
    }

}
