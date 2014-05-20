package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.ipet.R;
import com.ipet.android.app.ActivityManager;
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
	private long mExitTime;

	private TextSwitcher title_ts;
	private TextSwitcher ts;
	private String[] titleArray = { "记录", "分享", "发现" };
	private String[] poemArray = { "让手机记录我的宠物生活", "选择精彩记录分享到社交网络", "发现更多的宠物邻居和日常服务，这里有你需要的一些" };

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

		title_ts = (TextSwitcher) findViewById(R.id.welcome_title);
		ts = (TextSwitcher) findViewById(R.id.welcome_pane_caption);

		ts.setFactory(new ViewFactory() {
			public View makeView() {
				TextView tv = new TextView(WelcomeRegisterOrLoginActivity.this);
				tv.setTextAppearance(WelcomeRegisterOrLoginActivity.this, R.style.welcome_caption);
				tv.setShadowLayer(2, 1, 1, R.color.welcome_black_shadow);
				tv.setGravity(Gravity.CENTER);
				return tv;
			}
		});

		title_ts.setFactory(new ViewFactory() {
			public View makeView() {
				TextView tv = new TextView(WelcomeRegisterOrLoginActivity.this);
				tv.setTextAppearance(WelcomeRegisterOrLoginActivity.this, R.style.welcome_title);
				tv.setShadowLayer(2, 1, 1, R.color.welcome_black_shadow);
				tv.setGravity(Gravity.CENTER);
				return tv;
			}
		});

		// ts.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
		// android.R.anim.slide_in_left));
		// ts.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
		// android.R.anim.slide_out_right));
		ts.setText(poemArray[0]);
		title_ts.setText(titleArray[0]);
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
			ts.setText(poemArray[arg0]);
			title_ts.setText(titleArray[arg0]);
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
