package com.ipet.android.app;

import android.app.Application;
import com.ipet.android.sdk.core.IpetApi;

/**
 * 应用程序类 负责应用程序启动逻辑 常用共享对象的引用
 *
 * @author xiaojinghai
 */
public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();

    private IpetApi ipetApi;

    public IpetApi getApi() {
        return ipetApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        this.ipetApi = IpetApi.init(this);
    }

}
