package com.ipet.android.task;

import android.os.AsyncTask;
import android.util.Log;

import com.ipet.android.ui.adapter.ListFeedAdapter;
import com.ipet.android.ui.common.FeedListView;
import com.ipet.android.ui.manager.FeedManager;

public class FeedLoadAsyncTask extends AsyncTask<String, String, String> {
	private final FeedListView listView;
	private final ListFeedAdapter adapter;

	public FeedLoadAsyncTask(FeedListView listView, ListFeedAdapter adapter) {
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
		Log.i("FeedLoad", "FeedLoad" + FeedManager.load().size());
		adapter.loadList(FeedManager.load());
		listView.setLastUpdated("更新于:12-10 10:10");
		listView.onRefreshComplete();
		super.onPostExecute(result);
	}

}
