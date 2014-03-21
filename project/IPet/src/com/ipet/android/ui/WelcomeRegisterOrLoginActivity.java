package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.ui.manager.ActivityManager;
import com.ipet.android.ui.utils.AppUtils;

public class WelcomeRegisterOrLoginActivity extends Activity {

	private ViewPager viewPager;
	private ViewGroup group;

	private ImageView imageView;
	private ImageView[] imageViews;

	private int[] imgIdArray;
	private ImageView[] welcomeImageViews;

	private TextView loginBtn = null;
	private TextView regBtn = null;
	private TextView version = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_register_or_login);
		ActivityManager.getInstance().addActivity(this);
		// 初始化页面
		initViews();
		// 初始化小圆点
		initDots();

		// 初始化按钮
		initBtn();
	}

	private void initViews() {

		imgIdArray = new int[] { R.drawable.welcome_pic1, R.drawable.welcome_pic2, R.drawable.welcome_pic3 };

		welcomeImageViews = new ImageView[imgIdArray.length];
		for (int i = 0; i < welcomeImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(imgIdArray[i]);
			welcomeImageViews[i] = imageView;
		}

		viewPager = (ViewPager) findViewById(R.id.welcome_view_pager);
		viewPager.setAdapter(mPagerAdapter);
		viewPager.setOnPageChangeListener(welcomePageChangeListener);
	}

	private final PagerAdapter mPagerAdapter = new PagerAdapter() {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return welcomeImageViews.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object arg2) {
			((ViewPager) container).removeView(welcomeImageViews[position % welcomeImageViews.length]);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(welcomeImageViews[position % welcomeImageViews.length], 0);
			Log.i("welcome", "position->" + position);
			return welcomeImageViews[position % welcomeImageViews.length];

		}

	};

	private final OnPageChangeListener welcomePageChangeListener = new OnPageChangeListener() {

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
			setCurrentDot(arg0);
		}

	};

	private void initDots() {
		group = (ViewGroup) findViewById(R.id.dotViewGroup);
		// 将小圆点放到imageView数组当中
		imageViews = new ImageView[welcomeImageViews.length];
		for (int i = 0; i < welcomeImageViews.length; i++) {
			imageView = new ImageView(this);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(24, 24);
			lp.setMargins(10, 0, 10, 0);
			imageView.setLayoutParams(lp);
			imageViews[i] = imageView;
			group.addView(imageViews[i]);
		}
		setCurrentDot(0);
	}

	// 设置当前的样式
	private void setCurrentDot(int arg) {
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[arg].setBackgroundResource(R.drawable.dot_focused);
			if (arg != i) {
				imageViews[i].setBackgroundResource(R.drawable.dot_normal);
			}
		}
	}

	private void initBtn() {
		loginBtn = (TextView) this.findViewById(R.id.welcome_login_button);
		loginBtn.setOnClickListener(myWelcomeClick);

		regBtn = (TextView) this.findViewById(R.id.welcome_register_button);
		regBtn.setOnClickListener(myWelcomeClick);

		version = (TextView) this.findViewById(R.id.version_info);
		String verStr = getResources().getString(R.string.app_version);
		String v = String.format(verStr, AppUtils.getAppVersionName(this));
		version.setText(v);
	}

	private final OnClickListener myWelcomeClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			TextView btn = (TextView) v;
			switch (btn.getId()) {
			case R.id.welcome_login_button: {
				Intent intent = new Intent(WelcomeRegisterOrLoginActivity.this, LoginActivity.class);
				startActivity(intent);

				break;
			}

			case R.id.welcome_register_button: {
				Intent intent = new Intent(WelcomeRegisterOrLoginActivity.this, RegisterActivity.class);
				startActivity(intent);

				break;
			}
			}

		}

	};
}
