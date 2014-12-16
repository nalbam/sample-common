package com.nalbam.common.util;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

public class URLCheck {

	public static String getStatus(String str) {
		String status = "0";

		if (StringUtils.isEmpty(str)) {
			return status;
		}

		try {
			URL url = new URL(str);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			status = String.valueOf(connection.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();

			status = e.toString();
		}

		return status;
	}

}
