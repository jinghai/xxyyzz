package com.ipet.android.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.Constant;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.task.FeedLikedAsyncTask;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.utils.AnimUtils;
import com.ipet.android.ui.utils.StringUtils;
import com.loopj.android.image.SmartImageView;

public class PhotoViewActivity extends Activity {

	private SmartImageView photo;
	private SmartImageView avator;
	private TextView create_by;
	private TextView create_at;
	private TextView textView;
	public View comments_group;
	public CheckBox btn_liked;
	public TextView favor_count;
	public View likes_group;
	public IpetPhoto feed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_view);

		Intent intent = getIntent();
		feed = (IpetPhoto) intent.getSerializableExtra(Constant.IPET_PHOTO_SERIALIZABLE);

		Log.i("self0", "" + feed.isFavored());

		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.titlebar);
		titleBar.setLeftViewClick(new BackAndFinishClick(this));
		titleBar.setTitle(feed.getUserName());

		photo = (SmartImageView) findViewById(R.id.feed_content_image);
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		LayoutParams ps = (LayoutParams) photo.getLayoutParams();
		ps.width = width;
		ps.height = width;
		photo.setLayoutParams(ps);
		photo.setImageUrl(feed.getSmallURL());

		avator = (SmartImageView) findViewById(R.id.feed_avatar);
		create_by = (TextView) findViewById(R.id.feed_created_by);
		create_at = (TextView) findViewById(R.id.feed_created_at);
		avator.setImageUrl(feed.getAvatar48(), R.drawable.list_default_avatar_boy);
		// create_by.setText(feed.getUserName());
		create_at.setText(feed.getCreateAt());

		textView = (TextView) this.findViewById(R.id.row_feed_photo_textview_comments);
		comments_group = this.findViewById(R.id.row_feed_photo_comments_group);

		String text = feed.getText();

		if (!StringUtils.isEmpty(text)) {
			comments_group.setVisibility(View.VISIBLE);
			textView.setText(text);
		} else {
			comments_group.setVisibility(View.GONE);
		}

		// ---liked
		likes_group = this.findViewById(R.id.row_feed_photo_likes_group);
		favor_count = (TextView) this.findViewById(R.id.row_feed_photo_textview_likes);
		btn_liked = (CheckBox) this.findViewById(R.id.row_feed_photo_toggle_button_like);
		btn_liked.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CheckBox btn_liked = (CheckBox) v;
				boolean checked = btn_liked.isChecked();
				btn_liked.setChecked(!checked);
				new FeedLikedAsyncTask(PhotoViewActivity.this, PhotoViewActivity.this.feed, checked).execute();

			}
		});

		initFavor(this.feed);

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		this.unregisterReceiver(broadcastreciver);
	}

	private BroadcastReceiver broadcastreciver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Log.i("actionFind", action);
			IpetPhoto ipetPhoto = (IpetPhoto) intent.getSerializableExtra(Constant.IPET_PHOTO_SERIALIZABLE);
			initFavor(ipetPhoto);
		}

	};

	public void initFavor(IpetPhoto feed) {
		this.feed = feed;
		// TODO Auto-generated method stub
		if ("0".equals(feed.getFavorCount())) {
			likes_group.setVisibility(View.GONE);
		} else {
			likes_group.setVisibility(View.VISIBLE);
		}

		String likedNum = this.getResources().getString(R.string.likedNum);
		favor_count.setText(String.format(likedNum, feed.getFavorCount()));

		Log.i("self", "" + feed.isFavored());
		// TODO Auto-generated method stub
		btn_liked.setChecked(feed.isFavored());

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED);
		this.registerReceiver(broadcastreciver, filter);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AnimUtils.backAndFinish(this);
			return true;
		}
		return true;
	}

}
