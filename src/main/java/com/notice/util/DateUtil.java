package com.notice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private DateUtil() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * @param currentTime 
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws Exception
	 */
	public static String getCurrentTimeToDataFormat(long currentTime) throws Exception {
		Date d = new Date(currentTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	
	
}
