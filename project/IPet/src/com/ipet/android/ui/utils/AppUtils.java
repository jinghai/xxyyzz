package com.ipet.android.ui.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class AppUtils {
	public static String getAppVersionName(Context context) {   
	     String versionName = "";   
	     try {   
	         // ---get the package info---   
	         PackageManager pm = context.getPackageManager();   
	         PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);   
	         versionName = pi.versionName;   
	         if (versionName == null || versionName.length() <= 0) {   
	             return "";   
	         }   
	     } catch (Exception e) {   
	         Log.e("VersionInfo", "Exception", e);   
	     }   
	     return versionName;   
	 }  
	
	
	
}
