/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.android.sdk.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * @author Administrator
 */
public class StateManager {

    private static final String TAG = "StateManager";
    //是否已登录
    private static final String IS_AUTHORIZED = "isAuthorized";
    //当前用户ID
    private static final String UID = "uid";

    public static boolean getAuthorized(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        boolean ret = sp.getBoolean(IS_AUTHORIZED, false);
        return ret;
    }

    public static void setAuthorized(Context ctx, boolean isAuthorized) {
        SharedPreferences sp = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_AUTHORIZED, isAuthorized);
        editor.commit();
    }

    public static void setUid(Context ctx, String uid) {
        SharedPreferences sp = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(UID, uid);
        editor.commit();
    }

    public static String getUid(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        String uid = sp.getString(UID, null);
        return uid;
    }

}
