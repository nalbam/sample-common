package com.nalbam.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class RecordMapTest {

	@Test
	public void testRecordMap() {
		int number = 100;
		String string = "";
		String stnull = null;
		boolean bool = true;

		RecordMap rmap = new RecordMapSupport();
		rmap.put("number", number);
		rmap.put("string", string);
		rmap.put("null", stnull);
		rmap.put("bool", bool);
		rmap.put("list", new ArrayList<String>());
		rmap.put("array", new String[10]);

		assertEquals(rmap.getInt("number"), number);
		assertEquals(rmap.getString("string"), string);
		assertEquals(rmap.getString("stnull", string), string);
		assertTrue(rmap.getBoolean("bool"));
	}

}
