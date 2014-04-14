package com.ipet.android.task;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ipet.android.Constant;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetPhoto;

public class FeedLikedAsyncTask extends AsyncTask<String, String, Integer> {
	public final static String TAG = "FeedLikedAsyncTask";
	public final static int RESULT_SUCCESS = 0;
	public final static int RESULT_FAILURE = 1;

	private Activity activity;
	private boolean isLiked;
	private IpetPhoto ipetPhoto;

	public FeedLikedAsyncTask(Activity activity, IpetPhoto ipetPhoto, boolean isLiked) {
		this.activity = activity;
		this.ipetPhoto = ipetPhoto;
		this.isLiked = isLiked;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		int result = RESULT_FAILURE;
		try {
			MyApp application = (MyApp) this.activity.getApplication();
			if (isLiked) {
				ipetPhoto = application.getApi().getFavorApi().favor(ipetPhoto.getId(), "");
			} else {
				ipetPhoto = application.getApi().getFavorApi().unfavor(ipetPhoto.getId());
			}
			result = RESULT_SUCCESS;
		} catch (Exception e) {
			Log.e(TAG, "" + e.getLocalizedMessage());
		}
		return result;
	}

	@Override
	protected void onPostExecute(Integer result) {
		if (RESULT_FAILURE == result.intValue()) {
			Toast.makeText(this.activity, "操作失败", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent intent = new Intent(Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED);
		Bundle mBundle = new Bundle();
		mBundle.putSerializable(Constant.IPET_PHOTO_SERIALIZABLE, (Serializable) ipetPhoto);
		intent.putExtras(mBundle);
		this.activity.sendBroadcast(intent);
		Log.i(TAG, "sendBroadcast");
	}
}
