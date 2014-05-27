package com.ipet.android.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import com.ipet.R;
import com.ipet.android.sdk.core.IpetApi;
import com.ipet.android.sdk.util.UpdateManager;
import com.ipet.android.ui.manager.ActivityManager;
import com.ipet.android.ui.utils.AnimUtils;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private static String TAG = "MainActivity";
    private ArrayList<Fragment> fragmentsList;
    private ViewPager viewPager;
    private long mExitTime;
    private View actionbar_home_active;
    private View actionbar_find_active;
    public static int POP_INTENT_REQUEST = 999;
    private ImageView mTabImg;
    private int currIndex = 0;
    private int zero = 0;
    private int one;
    private int two;
    private int three;
    BroadcastReceiver mReceiver;
    MainHomeFragment mainHomeFragment;
    MainFindFragment mainFindFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        ActivityManager.getInstance().addActivity(this);
        // 初始化页面
        initViews();
        // 检查软件更新
        UpdateManager manager = new UpdateManager(MainActivity.this);
        //manager.checkUpdate();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    private void initViews() {

        ImageView rightBtn = (ImageView) findViewById(R.id.titlebar_right_btn);
        rightBtn.setOnClickListener(popMenuOnClick);

        actionbar_home_active = findViewById(R.id.actionbar_home_active);
        actionbar_find_active = findViewById(R.id.actionbar_find_active);

        actionbar_home_active.setOnClickListener(new TabClickListener(0));
        actionbar_find_active.setOnClickListener(new TabClickListener(1));

        mTabImg = (ImageView) this.findViewById(R.id.imageTabNow);

        fragmentsList = new ArrayList<Fragment>();

        mainHomeFragment = new MainHomeFragment();
        mainFindFragment = new MainFindFragment();

        fragmentsList.add(mainHomeFragment);
        fragmentsList.add(mainFindFragment);

        viewPager = (ViewPager) findViewById(R.id.tabpager);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        viewPager.setOnPageChangeListener(myPageChangeListener);

        int displayWidth = AnimUtils.dip2px(this, 44);
        one = displayWidth;
        two = one * 2;

    }

    public class TabClickListener implements OnClickListener {

        private int index = 0;

        public TabClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            viewPager.setCurrentItem(index);
        }

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
            Animation animation = null;
            switch (arg0) {
                case 0: {
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, zero, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, zero, 0, 0);
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, zero, 0, 0);
                    }
                    break;
                }
                case 1: {
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, one, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, one, 0, 0);
                    }

                    break;
                }
                case 2: {
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, two, 0, 0);
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, two, 0, 0);
                    }

                    break;
                }
                case 3: {
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, three, 0, 0);
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, three, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, three, 0, 0);
                    }
                    break;
                }

            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(150);
            mTabImg.startAnimation(animation);

        }

    };

    public OnClickListener popMenuOnClick = new OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(MainActivity.this, MainPopDialog.class);
            startActivityForResult(intent, POP_INTENT_REQUEST);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == POP_INTENT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                toLogout();
            }
        }
    }

    private void toLogout() {
        // TODO Auto-generated method stub
        IpetApi api = IpetApi.init(MainActivity.this);
        api.getAccountApi().logout();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        AnimUtils.backAndFinish(this);
    }

}
