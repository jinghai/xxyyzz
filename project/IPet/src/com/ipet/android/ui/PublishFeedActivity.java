package com.ipet.android.ui;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.ipet.R;
import com.ipet.android.task.FeedAddAsyncTask;
import com.ipet.android.ui.common.SimpleTitleBar;

public class PublishFeedActivity extends Activity {
	private ImageView imageView;
	private String path;
	private Uri pathUri;
	private EditText edit;
	private View publish_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish_feed);

		Intent intent = getIntent();
		path = intent.getStringExtra("PATH");

		SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.publish_titlebar);
		titleBar.setLeftViewClick(myback);

		File picture = new File(path);
		pathUri = Uri.fromFile(picture);

		imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setImageURI(pathUri);

		edit = (EditText) this.findViewById(R.id.editText);

		publish_btn = this.findViewById(R.id.publish_btn);
		publish_btn.setOnClickListener(publish);
	}

	private OnClickListener myback = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PublishFeedActivity.this.backAndfinish();
		}

	};

	private final OnClickListener publish = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Editable text = PublishFeedActivity.this.edit.getText();
			String str = text.toString();
			new FeedAddAsyncTask(PublishFeedActivity.this, path, str).execute();

		}

	};

	public void backAndfinish() {
		Intent intent = new Intent(PublishFeedActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			return true;
		}
		return true;
	}

}
