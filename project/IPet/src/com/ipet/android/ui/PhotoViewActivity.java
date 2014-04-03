package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.sdk.domain.IpetPhoto;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_view);

		Intent intent = getIntent();
		IpetPhoto feed = (IpetPhoto) intent.getSerializableExtra("FEED");

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

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

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
