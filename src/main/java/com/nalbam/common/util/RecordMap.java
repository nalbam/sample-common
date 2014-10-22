/**
 * FileName  : RecordMap.java
 * Author    : v.jyyu
 * Date      : 2012. 02. 02
 */
package com.nalbam.common.util;

import java.util.List;
import java.util.Map;

/**
 * @author v.jyyu
 * @since 2012. 02. 02
 */
public interface RecordMap extends Map<String, Object>, Cloneable {

	public void addAll(Map<?, ?> map);

	public Object get(String key, Object defaultValue);

	public String getString(String key);

	public String getString(String key, String defaultValue);

	public String[] getStrings(String key);

	public int[] getInts(String key);

	public boolean getBoolean(String key);

	public boolean getBoolean(String key, boolean defaultValue);

	public double getDouble(String key);

	public double getDouble(String key, double defaultValue);

	public float getFloat(String key);

	public float getFloat(String key, float defaultValue);

	public int getInt(String key);

	public int getInt(String key, int defaultValue);

	public long getLong(String key);

	public long getLong(String key, long defaultValue);

	public List<?> getValueList(String key);

	public Object put(String key, Object value, Object defaultValue);

	public String toSerialize();

}
