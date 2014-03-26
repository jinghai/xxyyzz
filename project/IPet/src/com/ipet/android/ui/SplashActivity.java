package com.ipet.android.ui;

import java.util.concurrent.CountDownLatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ipet.R;
import com.ipet.android.Constant;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetAppUpdate;
import com.ipet.android.task.SplashLoginAsyncTask;
import com.ipet.android.ui.manager.LoginManager;
import com.ipet.android.ui.manager.UpdateManager;
import com.ipet.android.ui.manager.UserManager;

public class SplashActivity extends Activity {

    boolean isLogin = false;

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

        // 初始化模拟用户数据
        MyApp application = (MyApp) this.getApplication();
        if (application.getUser() == null) {
            application.setUser(UserManager.getCurrentUser());
        }
        // 判断是否登录
        isLogin = LoginManager.isLogin(SplashActivity.this);
        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (isLogin) {
            // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
            // mHandler.sendEmptyMessageDelayed(GO_MAIN,
            // Constant.SPLASH_DELAY_MILLIS);
            // mHandler.sendEmptyMessageDelayed(GO_GUIDE,Constant.SPLASH_DELAY_MILLIS);
            String[] u = LoginManager.getAccount(SplashActivity.this);
            new SplashLoginAsyncTask(SplashActivity.this, u[0], u[1]).execute();
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
