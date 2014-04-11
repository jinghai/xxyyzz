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

public class FeedLikedAsyncTask extends AsyncTask<String, String, IpetPhoto> {
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
	protected IpetPhoto doInBackground(String... params) {
		// TODO Auto-generated method stub

		MyApp application = (MyApp) this.activity.getApplication();
		try {
			IpetPhoto feed;
			if (isLiked) {
				ipetPhoto = application.getApi().getFavorApi().favor(ipetPhoto.getId(), "");
			} else {
				ipetPhoto = application.getApi().getFavorApi().unfavor(ipetPhoto.getId());
			}
		} catch (Exception e) {
			ipetPhoto = null;
			Log.e("error", "" + e.getLocalizedMessage());
		}
		return ipetPhoto;
	}

	@Override
	protected void onPostExecute(IpetPhoto ipetPhoto) {
		if (ipetPhoto == null) {
			Toast.makeText(this.activity, "操作失败", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent intent = new Intent(Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED);
		Bundle mBundle = new Bundle();
		mBundle.putSerializable(Constant.IPET_PHOTO_SERIALIZABLE, (Serializable) ipetPhoto);
		intent.putExtras(mBundle);
		this.activity.sendBroadcast(intent);
		Log.i("sendBroadcast", "sendBroadcast");
	}
}
