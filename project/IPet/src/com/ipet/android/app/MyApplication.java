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

    private IpetApi ipetApi;

    public IpetApi getApi() {
        return ipetApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        this.ipetApi = new IpetApi(this);
    }

    public IpetUser getUser() {
        String uid = getApi().getCurrUserId();
        return null == uid ? null : getApi().getUserApi().getUser(uid);
    }

}
