package com.ipet.android.task;

import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ipet.android.app.MyApplication;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.MainHomeFragment;
import com.ipet.android.ui.adapter.ListFeedAdapter;
import com.ipet.android.ui.common.FeedListView;
import com.ipet.android.ui.utils.DateTimeUtils;

public class FeedLoadAsyncTask extends AsyncTask<String, String, Integer> {
	public final static String TAG = "FeedLoadAsyncTask";
	public final static int RESULT_SUCCESS = 0;
	public final static int RESULT_FAILURE = 1;

	private final FeedListView listView;
	private final ListFeedAdapter adapter;
	private final MainHomeFragment fragment;
	private final int type;
	private List<IpetPhoto> list = null;

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
	protected Integer doInBackground(String... params) {
		String timeline = params[0];
		String page = params[1];
		// TODO Auto-generated method stub
		int result = RESULT_FAILURE;
		try {
			MyApplication application = (MyApplication) this.fragment.getActivity().getApplication();
			this.list = application.getApi().getPhotoApi().listFollowd(timeline, page, String.valueOf(MainHomeFragment.LIST_SIZE));
			result = RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "" + e.getLocalizedMessage());
		}
		return result;
	}

	@Override
	protected void onPostExecute(Integer result) {
		if (RESULT_FAILURE == result.intValue()) {
			Toast.makeText(fragment.getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
			return;
		}

		if (type == MainHomeFragment.TYPE_CODE_LOAD) {
			this.adapter.loadList(list);
			listView.setLastUpdated("更新于:" + DateTimeUtils.getNowDateTime());
		} else if (type == MainHomeFragment.TYPE_CODE_MORE) {
			this.adapter.appendList(list);
		}

		listView.onLoadMoreComplete();
		if (list.size() < MainHomeFragment.LIST_SIZE) {
			this.listView.hideMoveView();
		} else {
			this.listView.showMoveView();
		}

		listView.onRefreshComplete();
		super.onPostExecute(result);
	}
}
