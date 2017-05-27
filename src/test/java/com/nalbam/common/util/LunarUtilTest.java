package com.nalbam.common.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LunarUtilTest {

    @Test
    public void testConvert() {
        String str1 = "1980-02-14";
        String str2 = "1979-12-28";

        String var1 = LunarUtil.toLunar(str1);
        String var2 = LunarUtil.toSolar(str2);

        System.out.println("양력 " + str1 + " 은 음력 " + var1 + " 입니다.");
        System.out.println("음력 " + str2 + " 은 양력 " + var2 + " 입니다.");

        assertEquals(str1, var2);
        assertEquals(str2, var1);
    }

    @Test
    public void testNull() {
        String var1 = LunarUtil.toLunar(null);
        String var2 = LunarUtil.toSolar(null);

        assertEquals(var1, "");
        assertEquals(var2, "");
    }

    @Test
    public void testLength() {
        String str1 = "111";
        String str2 = "222";

        String var1 = LunarUtil.toLunar(str1);
        String var2 = LunarUtil.toSolar(str2);

        assertEquals(var1, "");
        assertEquals(var2, "");
    }

}
