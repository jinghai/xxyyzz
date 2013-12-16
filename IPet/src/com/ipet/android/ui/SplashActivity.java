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

	// ��ʼ��ʼ��
	private void init() {
		// ��ȡSharedPreferences����Ҫ������
		// ʹ��SharedPreferences����¼�����ʹ�ô���
		SharedPreferences sp = getSharedPreferences(Constant.SP_SETTING_FILENAME, MODE_PRIVATE);
		// ȡ����Ӧ��ֵ�����û�и�ֵ��˵����δд�룬��true��ΪĬ��ֵ
		isFirstIn = sp.getBoolean(Constant.SP_SETTING_FIRST_RUN_KEY, true);
		// �жϳ�����ڼ������У�����ǵ�һ����������ת���������棬������ת��������
		if (!isFirstIn) {
			// ʹ��Handler��postDelayed������3���ִ����ת��MainActivity
			mHandler.sendEmptyMessageDelayed(GO_MAIN,Constant.SPLASH_DELAY_MILLIS);
			//mHandler.sendEmptyMessageDelayed(GO_GUIDE,Constant.SPLASH_DELAY_MILLIS);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, Constant.SPLASH_DELAY_MILLIS);
		}

	}

	private Handler mHandler = new Handler() {
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

	// ת��������
	protected void goMain() {
		Log.i("SPLASH", "to Main");
		//Intent intent = new Intent(SplashActivity.this, WelcomeRegisterOrLoginActivity.class);
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	// ת����������
	protected void goGuide() {
		Log.i("SPLASH", "to Guide");
		Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
		startActivity(intent);
		AnimUtils.pushLeftToRight(this);
		finish();
	}

}
