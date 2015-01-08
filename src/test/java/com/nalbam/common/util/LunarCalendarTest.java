package com.nalbam.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LunarCalendarTest {

	@Test
	public void testConvert() {
		String str1 = "1980-02-14";
		String str2 = "1979-12-28";

		String var1 = LunarCalendar.toLunar(str1);
		String var2 = LunarCalendar.toSolar(str2);

		assertEquals(str1, var2);
		assertEquals(str2, var1);
	}

	@Test
	public void testNull() {
		String str1 = null;
		String str2 = null;

		String var1 = LunarCalendar.toLunar(str1);
		String var2 = LunarCalendar.toSolar(str2);

		assertEquals(var1, "");
		assertEquals(var2, "");
	}

}
