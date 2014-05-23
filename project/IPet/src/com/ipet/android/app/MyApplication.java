package com.ipet.android.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.ipet.android.sdk.base.IpetApi;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.ui.manager.LoginManager;

/**
 * 应用程序类 负责应用程序启动逻辑 常用共享对象的引用
 *
 * @author xiaojinghai
 */
public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();

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
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        context = getApplicationContext();
        this.ipetApi = new IpetApi(this);
    }

    public IpetUser getUser() {
        return LoginManager.getUser(this);
    }

    public void setUser(IpetUser user) {
        LoginManager.setUser(context, user);
    }

}
