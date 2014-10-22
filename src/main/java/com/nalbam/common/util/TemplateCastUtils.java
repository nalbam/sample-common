/**
 * FileName  : TemplateCastUtils.java
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
public class TemplateCastUtils {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> toList(List<T> type, Object target) {
		if (target == null) {
			return null;
		}
		return (List) target;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <K, V> Map<K, V> toMap(Map<K, V> type, Object target) {
		if (target == null) {
			return null;
		}
		return (Map) target;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Comparable<T> toComparable(Comparable<T> type, Object target) {
		if (target == null) {
			return null;
		}
		return (Comparable) target;
	}

}
