package com.ipet.android.task;

import android.os.AsyncTask;

import com.ipet.android.ui.adapter.ListFeedAdapter;
import com.ipet.android.ui.common.FeedListView;

public class FeedLoadNewAsyncTask extends AsyncTask<String, String, String> {
	private final FeedListView listView;
	private final ListFeedAdapter adapter;

	public FeedLoadNewAsyncTask(FeedListView listView, ListFeedAdapter adapter) {
		this.listView = listView;
		this.adapter = adapter;
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
		// adapter.prependList(FeedManager.loadNew());
		listView.setLastUpdated("更新于:12-11 10:10");
		listView.onRefreshComplete();
		super.onPostExecute(result);
	}

}
