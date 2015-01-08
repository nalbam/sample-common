package com.nalbam.common.util;

import org.junit.Test;

public class LunarCalendarTest {

	@Test
	public void testExceptionIsThrown() {
		String str = null;

		str = "19800214";
		System.out.println(str + " -> " + LunarCalendar.toLunar(str));

		str = "19791228";
		System.out.println(str + " -> " + LunarCalendar.toSolar(str));
	}

}
