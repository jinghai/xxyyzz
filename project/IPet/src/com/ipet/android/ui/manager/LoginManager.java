package com.ipet.android.ui.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginManager {
	public static void saveAccountAndPassword(Activity activity, String username, String password) {
		Context ctx = activity;
		SharedPreferences sp = ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
		// 存入数据
		Editor editor = sp.edit();
		editor.putBoolean("isLogin", true);
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}

	public static boolean isLogin(Activity activity) {
		Context ctx = activity;
		SharedPreferences sp = ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
		boolean bl = sp.getBoolean("isLogin", false);
		return bl;
	}

	public static String[] getAccount(Activity activity) {
		Context ctx = activity;
		SharedPreferences sp = ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
		String username = sp.getString("username", "");
		String password = sp.getString("password", "");
		return new String[] { username, password };
	}

	public static void logout(Activity activity) {
		Context ctx = activity;
		SharedPreferences sp = ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isLogin", false);
		editor.putString("username", "");
		editor.putString("password", "");
		editor.commit();
	}
}
