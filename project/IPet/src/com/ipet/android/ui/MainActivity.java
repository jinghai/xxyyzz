package com.ipet.android.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.manager.ActivityManager;

public class MainActivity extends FragmentActivity {
	private ArrayList<Fragment> fragmentsList;
	private ViewPager viewPager;
	private long mExitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化页面
		initViews();
	}

	private void initViews() {

		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.main_home_titlebar);
		titleBar.setRightViewClick(popMenuOnClick);

		fragmentsList = new ArrayList<Fragment>();

		Fragment mainHomeFragment = new MainHomeFragment();
		Fragment mainConversationFragment = new MainConversationFragment();

		fragmentsList.add(mainHomeFragment);
		fragmentsList.add(mainConversationFragment);

		viewPager = (ViewPager) findViewById(R.id.tabpager);
		viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
		viewPager.setOnPageChangeListener(myPageChangeListener);
	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragmentsList;

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
			super(fm);
			this.fragmentsList = fragments;
		}

		@Override
		public int getCount() {
			return fragmentsList.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentsList.get(arg0);
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

	}

	private final OnPageChangeListener myPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub

		}

	};

	public OnClickListener popMenuOnClick = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(MainActivity.this, MainPopDialog.class);
			startActivity(intent);
		}
	};

	@Override
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
