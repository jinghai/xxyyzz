package com.ipet.android.ui.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ipet.android.sdk.domain.IpetUser;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class LoginManager {

    private static final String flag = "SP";
    private static final String IS_LOGIN = "isLogin";
    private static final String USER_NAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ID = "uid";
    private static final String USER = "user";

    public static void saveAccountAndPassword(Context activity, String username, String password) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        // 存入数据
        Editor editor = sp.edit();
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(USER_NAME, username);
        editor.putString(USER_PASSWORD, password);
        editor.commit();
    }

    public static boolean isLogin(Context activity) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        boolean bl = sp.getBoolean(IS_LOGIN, false);
        return bl;
    }

    public static String[] getAccount(Context activity) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        String username = sp.getString(USER_NAME, "");
        String password = sp.getString(USER_PASSWORD, "");
        String uid = sp.getString(USER_ID, "");
        return new String[]{username, password, uid};
    }

    public static void logout(Context activity) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(USER_NAME, "");
        editor.putString(USER_PASSWORD, "");
        editor.putString(USER_ID, "");
        editor.commit();
    }

    public static void setLogin(Context activity, boolean isLogin) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

    public static void setUid(Context activity, String uid) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(USER_ID, uid);
        editor.commit();
    }

    public static String getUid(Context activity) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        String uid = sp.getString(USER_ID, "");
        return uid;
    }

    public static String getUserName(Context activity) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        String str = sp.getString(USER_NAME, "");
        return str;
    }

    public static void setUser(Context activity, IpetUser user) {
        ObjectMapper mapper = new ObjectMapper();
        Writer strWriter = new StringWriter();
        try {
            mapper.writeValue(strWriter, user);
        } catch (IOException ex) {

        }
        String userDataJSON = strWriter.toString();
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(USER, userDataJSON);
        editor.commit();
    }

    public static IpetUser getUser(Context activity) {
        Context ctx = activity;
        SharedPreferences sp = ctx.getSharedPreferences(flag, Context.MODE_PRIVATE);
        String userDataJSON = sp.getString(USER, "null");
        ObjectMapper mapper = new ObjectMapper();
        IpetUser user = null;
        try {
            user = mapper.readValue(userDataJSON, IpetUser.class);
        } catch (IOException ex) {
        }
        return user;
    }
}
