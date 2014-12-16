package com.nalbam.common.util;

public class RuntimeMemory {

	public static RecordMap getStatus() {
		RecordMap mem = new RecordMapSupport();

		Runtime runtime = Runtime.getRuntime();

		long max = runtime.maxMemory();
		long total = runtime.totalMemory();
		long free = runtime.freeMemory();
		long used = total - free;

		mem.put("max", max);
		mem.put("free", free);
		mem.put("total", total);
		mem.put("used", used);

		return mem;
	}

}
