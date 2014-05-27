package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import com.ipet.R;
import com.ipet.android.app.Constant;
import com.ipet.android.sdk.core.IpetApi;
import com.ipet.android.ui.utils.AppUtils;

public class SplashActivity extends Activity {

    boolean isLogin = false;
    private TextView version = null;

    private static final int GO_MAIN = 1000;
    private static final int GO_WELCOME = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    // 开始初始化
    private void init() {
        version = (TextView) this.findViewById(R.id.version_info);
        String verStr = getResources().getString(R.string.app_version);
        String v = String.format(verStr, AppUtils.getAppVersionName(this));
        version.setText(v);

        IpetApi api = IpetApi.init(this);
        // 判断是否登录
        isLogin = api.getCurrUserId() != null;

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (isLogin) {
            this.goMain();
        } else {
            mHandler.sendEmptyMessageDelayed(GO_WELCOME, Constant.SPLASH_DELAY_MILLIS);
        }

    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_MAIN:
                    goMain();
                    break;
                case GO_WELCOME:
                    goWelcome();
                    break;
            }

            super.handleMessage(msg);
        }
    };

    // 转向主界面
    public void goMain() {
        Log.i("SPLASH", "to Main");
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    // 转向引导界面
    public void goWelcome() {
        Log.i("SPLASH", "to Welcome");
        Intent intent = new Intent(SplashActivity.this, WelcomeRegisterOrLoginActivity.class);
        startActivity(intent);
        finish();
    }

}
