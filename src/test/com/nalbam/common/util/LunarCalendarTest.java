package com.nalbam.common.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class LunarCalendarTest {

	@Test
	public void testConvert() {
		String str1 = "1980-02-14";
		String str2 = "1979-12-28";

		String var1 = LunarCalendar.toLunar(str1);
		String var2 = LunarCalendar.toSolar(str2);

		System.out.println("[" + str1 + "] == [" + var2 + "]");
		assertEquals(str1, var2);

		System.out.println("[" + str2 + "] == [" + var1 + "]");
		assertEquals(str2, var1);
	}

	@Test
	public void test() {
		String obj1 = "junit";
		String obj2 = "junit";
		String obj3 = "test";
		String obj4 = "test";
		String obj5 = null;
		int var1 = 1;
		int var2 = 2;
		int[] arithmetic1 = { 1, 2, 3 };
		int[] arithmetic2 = { 1, 2, 3 };

		assertEquals(obj1, obj2);

		assertSame(obj3, obj4);

		assertNotSame(obj2, obj4);

		assertNotNull(obj1);

		assertNull(obj5);

		assertTrue(var1 < var2);

		assertArrayEquals(arithmetic1, arithmetic2);
	}

}
