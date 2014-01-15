package com.ipet.android;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.ipet.client.api.IpetApi;

import com.ipet.client.api.domain.IpetUser;
import com.ipet.client.api.impl.IpetApiImpl;

/**
 * 应用程序类 负责应用程序启动逻辑 常用共享对象的引用
 *
 * @author yneos
 */
public class MyApp extends Application {

    private static final String TAG = MyApp.class.getSimpleName();

    private static Context context;
    private IpetApi ipetApi;
    public static final String APP_ID = "1";
    public static final String APP_SECRET = "1";
    private IpetUser user;

    public static Context getContext() {
        return context;
    }

    public IpetApi getApi() {
        return ipetApi;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, TAG + ":onCreate:" + APP_ID + "," + APP_SECRET);
        super.onCreate();
        context = getApplicationContext();
        this.ipetApi = IpetApiImpl.getInstance(APP_ID, APP_SECRET);
    }

    public IpetUser getUser() {
        return user;
    }

    public void setUser(IpetUser user) {
        this.user = user;
    }

}
