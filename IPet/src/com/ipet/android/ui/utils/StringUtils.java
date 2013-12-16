package com.ipet.android.ui.utils;


public class StringUtils {
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
}
