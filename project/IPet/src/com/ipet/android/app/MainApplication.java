package com.ipet.android.app;

import android.app.Application;
import android.content.Context;
import com.ipet.android.service.SocialService;

public class MainApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        SocialService.init(this);
    }

}
