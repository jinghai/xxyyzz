package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ipet.R;
import com.ipet.android.Constant;
import com.ipet.android.MyApp;
import com.ipet.android.ui.manager.UserManager;
import com.ipet.android.ui.utils.AnimUtils;

public class SplashActivity extends Activity {
	boolean isFirstIn = false;

	private static final int GO_MAIN = 1000;
	private static final int GO_GUIDE = 1001;

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

		// 读取SharedPreferences中需要的数据
		// 使用SharedPreferences来记录程序的使用次数
		SharedPreferences sp = getSharedPreferences(Constant.SP_SETTING_FILENAME, MODE_PRIVATE);
		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
		isFirstIn = sp.getBoolean(Constant.SP_SETTING_FIRST_RUN_KEY, true);
		// 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
		if (!isFirstIn) {
			// 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
			mHandler.sendEmptyMessageDelayed(GO_MAIN, Constant.SPLASH_DELAY_MILLIS);
			// mHandler.sendEmptyMessageDelayed(GO_GUIDE,Constant.SPLASH_DELAY_MILLIS);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, Constant.SPLASH_DELAY_MILLIS);
		}

	}

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_MAIN:
				goMain();
				break;
			case GO_GUIDE:
				goGuide();
				break;
			}
			super.handleMessage(msg);
		}
	};

	// 转向主界面
	protected void goMain() {
		Log.i("SPLASH", "to Main");
		Intent intent = new Intent(SplashActivity.this, WelcomeRegisterOrLoginActivity.class);
		// Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	// 转向引导界面
	protected void goGuide() {
		Log.i("SPLASH", "to Guide");
		Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
		startActivity(intent);
		AnimUtils.pushLeftToRight(this);
		finish();
	}

}
