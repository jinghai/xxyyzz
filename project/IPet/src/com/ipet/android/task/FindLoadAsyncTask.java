package com.ipet.android.task;

import java.util.List;

import android.os.AsyncTask;
import android.widget.GridView;

import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.MainFindFragment;
import com.ipet.android.ui.adapter.FindGridAdapter;

public class FindLoadAsyncTask extends AsyncTask<String, String, List<IpetPhoto>> {
	private final GridView gridView;
	private final FindGridAdapter adapter;
	private final int type;
	private final MainFindFragment fragment;

	public FindLoadAsyncTask(MainFindFragment fragment, GridView gridView, FindGridAdapter adapter, int type) {
		this.gridView = gridView;
		this.adapter = adapter;
		this.type = type;
		this.fragment = fragment;
	}

	@Override
	protected List<IpetPhoto> doInBackground(String... params) {
		// TODO Auto-generated method stub
		String timeline = params[0];
		String page = params[1];
		MyApp application = (MyApp) this.fragment.getActivity().getApplication();
		List<IpetPhoto> list = application.getApi().getDiscoverApi().listPage(timeline, page, String.valueOf(MainFindFragment.LIST_SIZE));
		return list;
	}

	@Override
	protected void onPostExecute(List<IpetPhoto> list) {
		// adapter.appendList(FeedManager.load());
		if (list != null) {
			// Toast.makeText(fragment.getActivity(), "加载完成",
			// Toast.LENGTH_SHORT).show();
			if (type == MainFindFragment.TYPE_CODE_LOAD) {
				this.adapter.loadList(list);
			} else if (type == MainFindFragment.TYPE_CODE_MORE) {
				this.adapter.appendList(list);
			}
		}
		super.onPostExecute(list);
	}

}
