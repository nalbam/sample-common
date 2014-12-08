package com.nalbam.common.sample;

import org.apache.commons.lang3.StringUtils;

/**
 * 파일설명 :
 * 수정내역 : 2014. 8. 25. / jyyu / 초기생성
 */
public class HelloWorldUtils {

	/**
	 * 함수설명 :
	 * 수정내역 : 2014. 8. 25. / jyyu / 초기생성
	 * 
	 * @return
	 */
	public static String getHello() {
		return StringUtils.upperCase("hello");
	}

	/**
	 * 함수설명 :
	 * 수정내역 : 2014. 8. 25. / jyyu / 초기생성
	 * 
	 * @return
	 */
	public static String getWorld() {
		return StringUtils.upperCase("world");
	}

}
