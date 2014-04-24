package net.ipetty.demo.sharesdk;

import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;

public class MainActivity extends ActionBarActivity implements Callback {

	private static final int MSG_SHARE_COMPLETE = 1;
	private static final int MSG_SHARE_ERROR = 2;
	private static final int MSG_SHARE_CANCEL = 3;
	private static final int MSG_USERID_FOUND = 4;
	private static final int MSG_LOGIN = 5;
	private static final int MSG_AUTH_CANCEL = 6;
	private static final int MSG_AUTH_ERROR = 7;
	private static final int MSG_AUTH_COMPLETE = 8;
	private static final String FILE_PATH_TEST_IMG = "/storage/extSdCard/logos/test_28.png";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShareSDK.initSDK(this);
		setContentView(R.layout.activity_main);

		OnClickListener onClickListener = new MyShareSDKButtonOnClickListener();
		findViewById(R.id.button1).setOnClickListener(onClickListener);
		findViewById(R.id.button2).setOnClickListener(onClickListener);
		findViewById(R.id.button3).setOnClickListener(onClickListener);
		findViewById(R.id.button4).setOnClickListener(onClickListener);
		findViewById(R.id.button5).setOnClickListener(onClickListener);
	}

	/**
	 * 定义各按钮的OnClickListener
	 */
	public class MyShareSDKButtonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1: {
				Platform platform = ShareSDK.getPlatform(v.getContext(), SinaWeibo.NAME);
				platform.setPlatformActionListener(new MainActivity.ShareListener());

				Platform.ShareParams shareParams = new SinaWeibo.ShareParams();
				shareParams.setText("测试分享到新浪微博");
				shareParams.setImagePath(FILE_PATH_TEST_IMG);
				platform.share(shareParams);
			}
				break;
			case R.id.button2: {
				oneKeyShare(v.getContext(), "测试一键分享，有界面，可编辑", FILE_PATH_TEST_IMG, true);
			}
				break;
			case R.id.button3: {
				oneKeyShare(v.getContext(), "测试一键分享，无界面，直接分享", FILE_PATH_TEST_IMG, true);
			}
				break;
			case R.id.button4: {
				authorize(new QZone(MainActivity.this));
			}
				break;
			case R.id.button5: {
				authorize(new SinaWeibo(MainActivity.this));
			}
				break;
			}
		}
	}

	/**
	 * 一键分享
	 */
	private void oneKeyShare(Context context, String text, String imagePath, boolean silent) {
		OnekeyShare oks = new OnekeyShare();
		oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		oks.setText(text);
		oks.setImagePath(imagePath);
		oks.setSilent(silent); // 是否直接分享，true则直接分享
		oks.setCallback(new MainActivity.ShareListener());
		oks.show(context);
	}

	/**
	 * 使用第三方帐号登录
	 */
	private void authorize(Platform platform) {
		if (platform.isValid()) { // 指定平台已经完成授权
			String userId = platform.getDb().getUserId();
			if (userId != null) {
				UIHandler.sendEmptyMessage(MSG_USERID_FOUND, MainActivity.this);
				login(platform.getName(), userId, null);
				return;
			}
		}
		platform.setPlatformActionListener(new MainActivity.AuthorizeListener());
		platform.SSOSetting(true); // true不使用SSO授权，false使用SSO授权
		platform.showUser(null);
	}

	/**
	 * 使用已授权帐号登录我们的应用（需要在之前授权完成后将用户信息写入我们的帐号数据库中）
	 */
	private void login(String platform, String userId, HashMap<String, Object> userInfo) {
		Message msg = new Message();
		msg.what = MSG_LOGIN;
		msg.obj = platform;
		UIHandler.sendMessage(msg, MainActivity.this);
		// TODO login to our app.
	}

	/**
	 * 分享到第三方平台操作的Listener
	 */
	public class ShareListener implements PlatformActionListener {
		@Override
		public void onComplete(Platform platform, int action, HashMap<String, Object> args) {
			UIHandler.sendEmptyMessage(MSG_SHARE_COMPLETE, MainActivity.this);
		}

		@Override
		public void onCancel(Platform platform, int action) {
			UIHandler.sendEmptyMessage(MSG_SHARE_CANCEL, MainActivity.this);
		}

		@Override
		public void onError(Platform platform, int action, Throwable t) {
			t.printStackTrace();
			UIHandler.sendEmptyMessage(MSG_SHARE_ERROR, MainActivity.this);
		}
	}

	/**
	 * 第三方帐号登录操作的Listener
	 */
	public class AuthorizeListener implements PlatformActionListener {
		@Override
		public void onComplete(Platform platform, int action, HashMap<String, Object> args) {
			if (action == Platform.ACTION_USER_INFOR) {
				UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, MainActivity.this);
				login(platform.getName(), platform.getDb().getUserId(), args);
			}
			System.out.println(args);
		}

		@Override
		public void onCancel(Platform platform, int action) {
			if (action == Platform.ACTION_USER_INFOR) {
				UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, MainActivity.this);
			}
		}

		@Override
		public void onError(Platform platform, int action, Throwable t) {
			if (action == Platform.ACTION_USER_INFOR) {
				UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, MainActivity.this);
			}
			t.printStackTrace();
		}
	}

	/**
	 * 处理异步消息
	 */
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case MSG_SHARE_COMPLETE:
			Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
			break;
		case MSG_SHARE_CANCEL:
			Toast.makeText(this, "取消分享", Toast.LENGTH_SHORT).show();
			break;
		case MSG_SHARE_ERROR:
			Toast.makeText(this, "分享失败", Toast.LENGTH_SHORT).show();
			break;
		case MSG_USERID_FOUND: {
			Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
		}
			break;
		case MSG_LOGIN: {
			// String text = getString(R.string.logining, msg.obj);
			// Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
			//
			// Builder builder = new Builder(this);
			// builder.setTitle(R.string.register);
			// builder.setMessage(R.string.after_auth);
			// builder.setPositiveButton(R.string.ok, null);
			// builder.create().show();

			Toast.makeText(this, "正在使用" + msg.obj + "的授权帐号登录" + getString(R.string.app_name), Toast.LENGTH_SHORT)
					.show();
		}
			break;
		case MSG_AUTH_CANCEL: {
			Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
		}
			break;
		case MSG_AUTH_ERROR: {
			Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
		}
			break;
		case MSG_AUTH_COMPLETE: {
			Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
		}
			break;
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		ShareSDK.stopSDK(this);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
