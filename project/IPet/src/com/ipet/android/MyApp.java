package com.ipet.android;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.ipet.android.sdk.api.Ipet;
import com.ipet.android.sdk.api.connect.IpetConnectionFactory;

/**
 * 应用程序类 负责应用程序启动逻辑 常用共享对象的引用
 *
 * @author yneos
 */
public class MyApp extends Application {

    private static final String TAG = MyApp.class.getSimpleName();

    private static Context context;
    private static Ipet IpetApi;
    public static final String APP_ID = "1";
    public static final String APP_SECRET = "1";
    

    public static Context getContext() {
        return context;
    }

    public static Ipet getApi() {
        return IpetApi;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, TAG + ":onCreate");
        super.onCreate();
        context = getApplicationContext();
        //this.IpetApi = new IpetConnectionFactory(APP_ID, APP_SECRET).createConnection(null).getApi();
        //SocialService.init(this);
    }

}
