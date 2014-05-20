package com.ipet.android.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.app.Constant;
import com.ipet.android.sdk.domain.IpetComment;
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
	public ImageView btn_comment;
	public View comments_num_group;
	public TextView comments_num;
	public LinearLayout comments_group_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_view);
		Log.i("PhotoViewActivity", "onCreate");

		Intent intent = getIntent();
		feed = (IpetPhoto) intent.getSerializableExtra(Constant.IPET_PHOTO_SERIALIZABLE);

		// Log.i("PhotoViewActivity", feed.toString());

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

		btn_comment = (ImageView) this.findViewById(R.id.row_feed_photo_button_comment);
		comments_num_group = this.findViewById(R.id.row_feed_photo_comments_num_group);
		comments_num = (TextView) this.findViewById(R.id.row_feed_photo_textview_comments_num);
		comments_group_list = (LinearLayout) this.findViewById(R.id.row_feed_photo_comments_list);

		btn_comment.setOnClickListener(myCommentClick);
		comments_num.setOnClickListener(myCommentClick);

		initCommentView(this.feed);

		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED);
		filter.addAction(Constant.BROADCAST_INTENT_IPET_PHOTO_COMMENT);
		registerReceiver(broadcastreciver, filter);

		Log.i("PhotoViewActivity", "registerReceiver");

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("PhotoViewActivity", "unregisterReceiver");
		this.unregisterReceiver(broadcastreciver);
	}

	private OnClickListener myCommentClick = new OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(PhotoViewActivity.this, CommentActivity.class);
			Bundle mBundle = new Bundle();
			mBundle.putSerializable(Constant.IPET_PHOTO_SERIALIZABLE, (Serializable) feed);
			intent.putExtras(mBundle);
			PhotoViewActivity.this.startActivity(intent);
		}
	};

	private void initCommentView(IpetPhoto feed) {
		// TODO Auto-generated method stub
		String commentNumStr = this.getResources().getString(R.string.commentNum);
		String commentsNum = feed.getCommentCount();

		comments_num.setText(String.format(commentNumStr, commentsNum));

		int size = feed.getComments().size();
		if (feed.getComments().size() == 0) {
			comments_num_group.setVisibility(View.GONE);
		} else {
			comments_num_group.setVisibility(View.VISIBLE);
		}

		comments_group_list.removeAllViews();
		List<IpetComment> list = new ArrayList<IpetComment>(5);
		if (size <= 5) {
			list = feed.getComments();
		} else {
			list = feed.getComments().subList(size - 5, size);
		}

		for (IpetComment comment : list) {
			comments_group_list.addView(addComment(comment));
		}
	}

	private View addComment(IpetComment comment) {
		String username = comment.getUserName();
		String text = comment.getText();
		RelativeLayout layout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.list_feed_item_feedback_comment, null);
		TextView t = (TextView) layout.findViewById(R.id.row_feed_photo_textview_comments_item);
		String link = this.getResources().getString(R.color.feed_link_color);
		link = link.substring(3, 9);
		t.setText(Html.fromHtml("<b><font color='#" + link + "'>" + username + "</font></b>&nbsp;" + text));
		return layout;
	}

	private void updateCommentView(String type, IpetComment comment) {
		// TODO Auto-generated method stub
		if (Constant.IPET_COMMENT_TYPE_ADD.equals(type)) {
			this.updateAddComment(comment);
		}
	}

	private void updateAddComment(IpetComment comment) {
		// TODO Auto-generated method stub
		this.feed.getComments().add(comment);
		this.feed.setCommentCount(Integer.toString(this.feed.getComments().size()));
		initCommentView(this.feed);
	}

	private BroadcastReceiver broadcastreciver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Log.i("action", action);
			if (Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED.equals(action)) {
				Log.i("initFavor", "initFavor");
				IpetPhoto ipetPhoto = (IpetPhoto) intent.getSerializableExtra(Constant.IPET_PHOTO_SERIALIZABLE);
				initFavor(ipetPhoto);
			}

			if (Constant.BROADCAST_INTENT_IPET_PHOTO_COMMENT.equals(action)) {
				IpetComment comment = (IpetComment) intent.getSerializableExtra(Constant.IPET_COMMENT_SERIALIZABLE);
				String type = (String) intent.getStringExtra(Constant.IPET_COMMENT_TYPE);
				updateCommentView(type, comment);

			}

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
		btn_liked.setChecked(feed.isFavored());

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

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
