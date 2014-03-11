package com.ipet.android.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	public static String date2Str(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat.format(date);
	}

	public static String getNowDateTime() {
		return date2Str(new Date());
	}
}
