package com.nalbam.common.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;

public class TimeUtilTest {

    @Test
    public void getAgo() {
        final Calendar c = Calendar.getInstance();
        c.set(2007, Calendar.APRIL, 28);

        final String ago = TimeUtil.ago(c);

        final SimpleDateFormat f = new SimpleDateFormat("yyyy년 MM월 dd일");

        System.out.println(f.format(c.getTime()) + " 은 " + ago + " 입니다.");

        assertNotNull(ago);
    }

}
