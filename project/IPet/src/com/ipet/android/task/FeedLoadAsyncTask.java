package com.ipet.android.task;

import java.util.List;

import android.os.AsyncTask;
import android.widget.Toast;

import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.MainHomeFragment;
import com.ipet.android.ui.adapter.ListFeedAdapter;
import com.ipet.android.ui.common.FeedListView;
import com.ipet.android.ui.utils.DateTimeUtils;

public class FeedLoadAsyncTask extends AsyncTask<String, String, List<IpetPhoto>> {
	private final FeedListView listView;
	private final ListFeedAdapter adapter;
	private final MainHomeFragment fragment;

	public FeedLoadAsyncTask(MainHomeFragment fragment, FeedListView listView, ListFeedAdapter adapter) {
		this.listView = listView;
		this.adapter = adapter;
		this.fragment = fragment;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		Toast.makeText(fragment.getActivity(), "加载中...", Toast.LENGTH_LONG).show();
		super.onPreExecute();
	}

	@Override
	protected List<IpetPhoto> doInBackground(String... params) {
		// TODO Auto-generated method stub
		MyApp application = (MyApp) this.fragment.getActivity().getApplication();
		List<IpetPhoto> list = application.getApi().getPhotoApi().listFollowd(DateTimeUtils.getNowDateTime(), "0", "10");
		return list;
	}

	@Override
	protected void onPostExecute(List<IpetPhoto> list) {
		if (list == null) {
			return;
		}

		Toast.makeText(fragment.getActivity(), "加载完成", Toast.LENGTH_SHORT).show();
		this.adapter.loadList(list);
		listView.setLastUpdated("更新于:" + DateTimeUtils.getNowDateTime());
		listView.onRefreshComplete();
		super.onPostExecute(list);
	}

}
