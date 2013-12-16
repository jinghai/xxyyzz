package com.ipet.android.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.ui.manager.ActivityManager;

public class MainActivity extends FragmentActivity {
	private static String TAG_INDEX = "0";
	private static String TAG_FIND = "1";
	private static String TAG_CAMERA = "2";
	private static String TAG_MESSAGE = "3";
	private static String TAG_ME = "4";
	private long mExitTime;
	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;
	private LayoutInflater layoutInflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActivityManager.getInstance().addActivity(this);
		
		initView();
	}

	private void initView() {
		layoutInflater = LayoutInflater.from(this);
		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		this.addTab(TAG_INDEX, R.string.main_home_title, R.drawable.tab_icon_home, MainHomeFragment.class);
		this.addTab(TAG_FIND, R.string.main_find_title, R.drawable.tab_icon_find, MainFindFragment.class);
		this.addTab(TAG_CAMERA, R.string.main_camera_title, R.drawable.tab_icon_camera, MainCameraFragment.class);
		this.addTab(TAG_MESSAGE, R.string.main_conversation_title, R.drawable.tab_icon_conversation, MainConversationFragment.class);
		this.addTab(TAG_ME, R.string.main_me_title, R.drawable.tab_icon_me, MainMeFragment.class);

		mTabHost.setCurrentTab(0);

	}

	private void addTab(String tag, int resId, int resImgId, Class<?> classname) {
		TabSpec tabSpec = mTabHost.newTabSpec(tag).setIndicator(getTabHostBtn(resId, resImgId));
		mTabHost.addTab(tabSpec, classname, null);
	}

	private View getTabHostBtn(int resId, int resImgId) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(resImgId);
		view.setBackgroundResource(R.drawable.tab_background);
		// String str = (String) this.getResources().getText(resId);
		return view;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				ActivityManager.getInstance().exit();
				// moveTaskToBack(true);                
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

}
