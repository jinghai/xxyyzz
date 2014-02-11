package com.ipet.android.task;

import android.os.AsyncTask;
import android.widget.GridView;

import com.ipet.android.ui.adapter.FindGridAdapter;
import com.ipet.android.ui.manager.FeedManager;

public class FindLoadAsyncTask extends AsyncTask<String, String, String> {
	private final GridView gridView;
	private final FindGridAdapter adapter;

	public FindLoadAsyncTask(GridView gridView, FindGridAdapter adapter) {
		this.gridView = gridView;
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
		adapter.appendList(FeedManager.load());
		super.onPostExecute(result);
	}

}
