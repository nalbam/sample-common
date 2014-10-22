/**
 * FileName  : RecordMapSupport.java
 * Author    : v.jyyu
 * Date      : 2012. 02. 02
 */
package com.nalbam.common.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author v.jyyu
 * @since 2012. 02. 02
 */
public class RecordMapSupport implements RecordMap, Serializable {

	private static final long serialVersionUID = 6528112113603056866L;

	private static final Pattern PARAM_DELIMETER_PATTERN = Pattern.compile(",\\s*");

	private static Log log = null;

	protected Map<String, Object> map = null;

	public static final String EMPTY_STRING = "";

	/**
	 * empty map을 생성합니다.
	 */
	public RecordMapSupport() {
		this.map = new LinkedHashMap<String, Object>();
		try {
			if (log != null) {
				log = LogFactory.getLog(RecordMapSupport.class);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 주어진 map 개체를 wrap하여 서비스됩니다.
	 * 
	 * @param map
	 */
	public RecordMapSupport(Map<?, ?> map) {
		if (map == null) {
			this.map = new LinkedHashMap<String, Object>();
		} else {
			this.map = TemplateCastUtils.toMap(this.map, map);
		}
		try {
			if (log != null) {
				log = LogFactory.getLog(RecordMapSupport.class);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * K = V, K = V, .... 형식의 문자열을 map 인스턴스로 생성합니다.
	 * 
	 * @param string
	 */
	public RecordMapSupport(String string) {
		this.map = new LinkedHashMap<String, Object>();
		parse(string);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#addAll(java.util.Map)
	 */
	public void addAll(Map<?, ?> map) {
		if (map == null) {
			return;
		}
		if (this.map == null) {
			this.map = new RecordMapSupport();
		}

		Iterator<?> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = map.get(key);

			if (value == null) {
				continue;
			}

			if (!(value instanceof String[])) {
				value = new String[] { String.valueOf(value) };
			}

			String[] values = (String[]) value;
			if (values.length == 1) {
				this.map.put(String.valueOf(key), values[0]);
			} else {
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < values.length; i++) {
					list.add(values[i]);
				}
				this.map.put(String.valueOf(key), list);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	public int size() {
		return map.size();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		map.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object key) {
		return map.containsValue(key);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	public Collection<Object> values() {
		return map.values();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	public Set<Map.Entry<String, Object>> entrySet() {
		return map.entrySet();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	public Set<String> keySet() {
		return map.keySet();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Object get(Object key) {
		Object value = map.get(key);

		if (value instanceof Object[]) {
			Object[] values = (Object[]) value;

			if (values.length == 1) {
				return values[0];
			}
		}

		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#get(java.lang.String, java.lang.Object)
	 */
	public Object get(String key, Object defaultValue) {
		if (containsKey(key)) {
			return map.get(key);
		}
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getString(java.lang.String)
	 */
	public String getString(String key) {
		return getString(key, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getString(java.lang.String, java.lang.String)
	 */
	public String getString(String key, String defaultValue) {
		if (!containsKey(key)) {
			return defaultValue;
		}

		Object value = get(key, defaultValue);

		if (value == null) {
			return defaultValue;
		} else if (value instanceof String[]) {
			String[] strings = (String[]) value;

			if (strings.length == 0 || strings[0] == null) {
				return defaultValue;
			}
			return strings[0];
		} else if (value instanceof List<?>) {
			List<?> list = ((List<?>) value);

			if (list.size() == 0 || list.get(0) == null) {
				return defaultValue;
			}
			return String.valueOf(list.get(0));
		}

		return String.valueOf(value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getStrings(java.lang.String)
	 */
	public String[] getStrings(String key) {
		Object value = get(key, null);

		if (value == null) {
			return null;
		} else if (value instanceof String[]) {
			return (String[]) value;
		}

		Object[] values = getValueList(key).toArray();

		if (values == null || values.length == 0) {
			return null;
		}

		String[] strings = new String[values.length];

		for (int i = 0; i < values.length; i++) {
			strings[i] = String.valueOf(values[i]);
		}

		return strings;

	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Object remove(Object key) {
		return map.remove(key);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.topas.ota.common.util.RecordMap#put(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public Object put(String key, Object value, Object defaultValue) {
		if (value == null) {
			value = defaultValue;
		}
		return map.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getBoolean(java.lang.String)
	 */
	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getBoolean(java.lang.String, boolean)
	 */
	public boolean getBoolean(String key, boolean defaultValue) {
		if (!containsKey(key)) {
			return defaultValue;
		}

		String value = getString(key);

		if (value == null) {
			return defaultValue;
		} else if (value.equalsIgnoreCase("true") || value.equals("1") || value.equalsIgnoreCase("yes")) {
			return true;
		} else if (value.equalsIgnoreCase("false") || value.equals("0") || value.equalsIgnoreCase("no")) {
			return false;
		}

		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getDouble(java.lang.String)
	 */
	public double getDouble(String key) {
		return getDouble(key, 0.0);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getDouble(java.lang.String, double)
	 */
	public double getDouble(String key, double defaultValue) {
		if (!containsKey(key)) {
			return defaultValue;
		}

		String value = getString(key);

		if (value == null || value.length() == 0) {
			return defaultValue;
		}

		try {
			return Double.valueOf(value).doubleValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getFloat(java.lang.String)
	 */
	public float getFloat(String key) {
		return getFloat(key, 0.0f);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getFloat(java.lang.String, float)
	 */
	public float getFloat(String key, float defaultValue) {
		if (!containsKey(key)) {
			return defaultValue;
		}
		return (float) getDouble(key, (double) defaultValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getInt(java.lang.String)
	 */
	public int getInt(String key) {
		return getInt(key, 0);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getInt(java.lang.String, int)
	 */
	public int getInt(String key, int defaultValue) {
		if (!containsKey(key)) {
			return defaultValue;
		}
		return (int) getLong(key, (long) defaultValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getLong(java.lang.String)
	 */
	public long getLong(String key) {
		return getLong(key, 0L);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getLong(java.lang.String, long)
	 */
	public long getLong(String key, long defaultValue) {
		if (!containsKey(key)) {
			return defaultValue;
		}

		Object value = get(key);

		if (value == null) {
			return defaultValue;
		} else if (value instanceof Timestamp) {
			return ((Timestamp) value).getTime();
		} else if (value instanceof java.util.Date) {
			return ((java.util.Date) value).getTime();
		} else if (value instanceof Number) {
			return ((Number) value).longValue();
		}

		try {
			return Long.parseLong(getString(key));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getValues(java.lang.String)
	 */
	public List<?> getValueList(String key) {
		List<Object> list = new ArrayList<Object>();

		if (!containsKey(key)) {
			return list;
		}

		Object value = get(key);

		if (value == null) {
			return list;
		}
		if (value instanceof Object[]) {
			Object[] values = (Object[]) value;
			return Arrays.asList(values);
		} else if (value instanceof List<?>) {
			return (List<?>) value;
		} else if (value instanceof Collection<?>) {
			return new ArrayList<Object>((Collection<?>) value);
		}

		list.add(value);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return map.toString();
	}

	/**
	 * Map 내의 데이터만 추출 합니다.
	 * 
	 * @return
	 */
	public String toSerialize() {
		if (map == null) {
			return null;
		}
		Iterator<Object> iterator = map.values().iterator();
		String value = "";
		while (iterator.hasNext()) {
			if (value != null && !value.equals("")) {
				value += "|";
			}
			value += String.valueOf(iterator.next());
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		if (map instanceof HashMap<?, ?>) {
			return new RecordMapSupport((Map<?, ?>) ((HashMap<?, ?>) map).clone());
		} else if (map instanceof TreeMap) {
			return new RecordMapSupport((Map<?, ?>) ((TreeMap<?, ?>) map).clone());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nalbam.commons.util.RecordMap#getInts(java.lang.String)
	 */
	public int[] getInts(String key) {
		if (!containsKey(key)) {
			return null;
		}

		String[] strings = getStrings(key);

		if (strings == null) {
			return null;
		}

		int[] ints = new int[strings.length];

		for (int i = 0; i < strings.length; i++) {
			try {
				ints[i] = Integer.parseInt(strings[i]);
			} catch (Exception e) {
				if (log.isTraceEnabled()) {
					log.trace("conversion exception[=" + e.getMessage() + "]");
				}
			}
		}
		return ints;
	}

	/**
	 * K = V, K = V, ... 형식의 문자열을 엔트리로 추가합니다.
	 * 
	 * @param param
	 */
	public void parse(String param) {
		if (log.isTraceEnabled()) {
			log.trace("parse constructor param string..." + param);
		}

		String values[] = PARAM_DELIMETER_PATTERN.split(param);

		for (int i = 0; i < values.length; i++) {
			String value = values[i];

			int pos = value.indexOf('=');

			if (pos >= 0) {
				put(value.substring(0, pos).trim(), value.substring(pos + 1));
			} else {
				put(value.trim(), EMPTY_STRING);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return map.equals(o);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends String, ? extends Object> subMap) {
		map.putAll(subMap);
	}

}
