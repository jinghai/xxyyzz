package com.ipet.android.task;

import java.io.Serializable;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.Constant;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetComment;
import com.ipet.android.sdk.domain.IpetPhoto;

public class CommentAsyncTask extends AsyncTask<String, String, IpetComment> {

	private Activity activity;
	private ProgressDialog progress;
	private IpetPhoto feed;

	public CommentAsyncTask(Activity activity, IpetPhoto feed) {
		this.activity = activity;
		this.feed = feed;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		String str = this.activity.getResources().getString(R.string.submit_loading);
		this.progress = ProgressDialog.show(this.activity, null, str);
	}

	@Override
	protected IpetComment doInBackground(String... params) {
		// TODO Auto-generated method stub
		IpetComment comment = null;
		String str = params[0];
		try {
			MyApp application = (MyApp) this.activity.getApplication();
			comment = application.getApi().getCommentApi().comment(this.feed.getId(), str);
		} catch (Exception e) {
			Log.e("error", "" + e.getLocalizedMessage());
		}

		this.progress.dismiss();
		return comment;
	}

	@Override
	protected void onPostExecute(IpetComment comment) {
		if (comment == null) {
			Toast.makeText(this.activity, "评论发送失败", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent intent = new Intent(Constant.BROADCAST_INTENT_IPET_PHOTO_COMMENT);
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.IPET_COMMENT_TYPE, Constant.IPET_COMMENT_TYPE_ADD);
		mBundle.putSerializable(Constant.IPET_COMMENT_SERIALIZABLE, (Serializable) comment);
		intent.putExtras(mBundle);
		this.activity.sendBroadcast(intent);
	}
}
