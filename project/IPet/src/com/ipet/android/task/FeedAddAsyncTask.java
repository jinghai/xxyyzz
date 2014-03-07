package com.ipet.android.task;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.ipet.android.ui.MainHomeFragment;
import com.ipet.android.ui.manager.FeedManager;
import com.ipet.android.vo.Feed;

public class FeedAddAsyncTask extends AsyncTask<String, String, String> {
	private final Uri uri;
	private final Fragment fragment;

	public FeedAddAsyncTask(Fragment fragment, Uri uri) {
		this.uri = uri;
		this.fragment = fragment;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	protected void onPostExecute(String result) {
		Feed feed = new Feed();
		feed.setCreate_by("kongchun");
		feed.setCreate_at("11:11");
		feed.setContent("");
		feed.setContent_image(uri.toString());
		FeedManager.list.add(0, feed);

		((MainHomeFragment) fragment).backToFeed();
	}
}
