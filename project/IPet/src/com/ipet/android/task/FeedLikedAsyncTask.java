package com.ipet.android.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.MainActivity;
import com.ipet.android.ui.PhotoViewActivity;

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

		if (this.activity instanceof MainActivity) {
			((MainActivity) this.activity).updateItem(ipetPhoto);
			return;
		}

		if (this.activity instanceof PhotoViewActivity) {
			((PhotoViewActivity) this.activity).initFavor(ipetPhoto);
			return;
		}

	}
}