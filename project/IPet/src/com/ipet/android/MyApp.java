package com.ipet.android;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.ipet.android.sdk.IpetApi;

import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.sdk.impl.IpetApiImpl;

/**
 * 应用程序类 负责应用程序启动逻辑 常用共享对象的引用
 *
 * @author xiaojinghai
 */
public class MyApp extends Application {

    private static final String TAG = MyApp.class.getSimpleName();

    private static Context context;
    private IpetApi ipetApi;
    public static final String APP_ID = "ipet";
    public static final String APP_SECRET = "ipet";
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
