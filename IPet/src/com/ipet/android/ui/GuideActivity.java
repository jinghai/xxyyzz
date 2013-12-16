package com.ipet.android.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ipet.R;
import com.ipet.android.Constant;

public class GuideActivity extends Activity {
	private ViewPager viewPager;
	private ArrayList<View> pageViews;
	private ViewGroup group;

	private ImageView imageView;
	private ImageView[] imageViews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		// 初始化页面
		initViews();
		// 初始化小圆点
		initDots();
	}

	private void initViews() {
		// 将要显示的图片放到ArrayList当中，存到适配器中
		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();
		pageViews.add(inflater.inflate(R.layout.activity_guide_item1, null));
		pageViews.add(inflater.inflate(R.layout.activity_guide_item2, null));
		pageViews.add(inflater.inflate(R.layout.activity_guide_item3, null));

		viewPager = (ViewPager) findViewById(R.id.guidePages);
		viewPager.setAdapter(mPagerAdapter);
		viewPager.setOnPageChangeListener(guidePageChangeListener);
	}

	private PagerAdapter mPagerAdapter = new PagerAdapter() {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object arg2) {
			((ViewPager) container).removeView(pageViews.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(pageViews.get(position));
			Log.i("Guide", "position->" + position);
			setLastPosition(container, position);
			return pageViews.get(position);

		}

		private void setLastPosition(View container, int position) {
			if (position != (pageViews.size() - 1)) {
				return;
			}
			Button guideBtn = (Button) container.findViewById(R.id.guide_btn);
			guideBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View btn) {
					setGuided();
					goWelcome();
				}

			});
		}

	};

	private void setGuided() {
		SharedPreferences preferences = getSharedPreferences(
				Constant.SP_SETTING_FILENAME, Context.MODE_PRIVATE
						+ Context.MODE_APPEND);
		Editor editor = preferences.edit();
		// 存入数据
		editor.putBoolean(Constant.SP_SETTING_FIRST_RUN_KEY, false);
		// 提交修改
		editor.commit();
	}

	private void goWelcome() {
		Intent intent = new Intent(GuideActivity.this,
				WelcomeRegisterOrLoginActivity.class);
		startActivity(intent);

		finish();
	}

	private OnPageChangeListener guidePageChangeListener = new OnPageChangeListener() {

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
		imageViews = new ImageView[pageViews.size()];
		for (int i = 0; i < pageViews.size(); i++) {
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

}
