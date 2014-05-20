package com.ipet.android.ui;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.app.Constant;
import com.ipet.android.sdk.domain.IpetComment;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.task.CommentAsyncTask;
import com.ipet.android.ui.adapter.ListCommentAdapter;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.utils.AnimUtils;
import com.ipet.android.ui.utils.StringUtils;

public class CommentActivity extends Activity {
	public IpetPhoto feed;
	public EditText editText;
	public TextView button;
	public ListCommentAdapter adapter;
	public ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);

		Intent intent = getIntent();
		feed = (IpetPhoto) intent.getSerializableExtra(Constant.IPET_PHOTO_SERIALIZABLE);

		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.titlebar);
		titleBar.setLeftViewClick(new BackAndFinishClick(this));

		editText = (EditText) this.findViewById(R.id.editText);
		button = (TextView) this.findViewById(R.id.button);
		button.setOnClickListener(sumbit);

		listView = (ListView) this.findViewById(R.id.listView);

		List<IpetComment> list = feed.getComments();
		adapter = new ListCommentAdapter(this, listView, list);
		listView.setAdapter(adapter);
		listView.setSelection(listView.getCount() - 1);

	}

	private final OnClickListener sumbit = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Editable text = CommentActivity.this.editText.getText();
			String str = text.toString();
			if (StringUtils.isEmpty(str)) {
				Toast.makeText(CommentActivity.this, R.string.feedback_no_empty, Toast.LENGTH_SHORT).show();
				return;
			}

			new CommentAsyncTask(CommentActivity.this, CommentActivity.this.feed).execute(str);

			CommentActivity.this.editText.setText("");
			CommentActivity.this.editText.clearFocus();
		}

	};

	@Override
	public void onStart() {
		super.onStart();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.BROADCAST_INTENT_IPET_PHOTO_COMMENT);
		this.registerReceiver(broadcastreciver, filter);
	}

	private BroadcastReceiver broadcastreciver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();

			if (Constant.BROADCAST_INTENT_IPET_PHOTO_COMMENT.equals(action)) {
				IpetComment comment = (IpetComment) intent.getSerializableExtra(Constant.IPET_COMMENT_SERIALIZABLE);
				String type = (String) intent.getStringExtra(Constant.IPET_COMMENT_TYPE);
				CommentActivity.this.updateComment(type, comment);
			}
		}

	};

	private void updateComment(String type, IpetComment comment) {
		// TODO Auto-generated method stub
		if (Constant.IPET_COMMENT_TYPE_ADD.equals(type)) {
			this.adapter.appendComment(comment);
		}

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		this.unregisterReceiver(broadcastreciver);
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
