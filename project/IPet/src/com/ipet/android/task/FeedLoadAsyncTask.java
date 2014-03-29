package com.ipet.android.task;

import java.util.List;

import android.os.AsyncTask;

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
	private final int type;

	public FeedLoadAsyncTask(MainHomeFragment fragment, FeedListView listView, ListFeedAdapter adapter, int type) {
		this.listView = listView;
		this.adapter = adapter;
		this.fragment = fragment;
		this.type = type;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		// Toast.makeText(fragment.getActivity(), "加载中...",
		// Toast.LENGTH_LONG).show();
		super.onPreExecute();
	}

	@Override
	protected List<IpetPhoto> doInBackground(String... params) {
		String timeline = params[0];
		String page = params[1];
		// TODO Auto-generated method stub
		MyApp application = (MyApp) this.fragment.getActivity().getApplication();
		List<IpetPhoto> list = application.getApi().getPhotoApi().listFollowd(timeline, page, String.valueOf(MainHomeFragment.LIST_SIZE));
		// List<IpetPhoto> list =
		// application.getApi().getPhotoApi().listFollowd(timeline, page,
		// String.valueOf(MainHomeFragment.LIST_SIZE));
		return list;
	}

	@Override
	protected void onPostExecute(List<IpetPhoto> list) {
		if (list != null) {
			// Toast.makeText(fragment.getActivity(), "加载完成",
			// Toast.LENGTH_SHORT).show();
			if (type == MainHomeFragment.TYPE_CODE_LOAD) {
				this.adapter.loadList(list);
				listView.setLastUpdated("更新于:" + DateTimeUtils.getNowDateTime());
			} else if (type == MainHomeFragment.TYPE_CODE_MORE) {
				this.adapter.appendList(list);
			}

			if (list.size() < MainHomeFragment.LIST_SIZE) {
				this.listView.hideMoveView();
			} else {
				this.listView.showMoveView();
			}
		}
		listView.onRefreshComplete();
		super.onPostExecute(list);
	}
}
