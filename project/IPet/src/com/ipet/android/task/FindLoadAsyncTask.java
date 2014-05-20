package com.ipet.android.task;

import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.ipet.android.app.MyApplication;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.MainFindFragment;
import com.ipet.android.ui.adapter.FindGridAdapter;

public class FindLoadAsyncTask extends AsyncTask<String, String, Integer> {
	public final static String TAG = "FeedLoadAsyncTask";
	public final static int RESULT_SUCCESS = 0;
	public final static int RESULT_FAILURE = 1;

	private List<IpetPhoto> list;
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
	protected Integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		int result = RESULT_FAILURE;
		try {

			String timeline = params[0];
			String page = params[1];

			MyApplication application = (MyApplication) this.fragment.getActivity().getApplication();
			list = application.getApi().getDiscoverApi().listPage(timeline, page, String.valueOf(MainFindFragment.LIST_SIZE));
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

		// adapter.appendList(FeedManager.load());
		if (list != null) {
			if (type == MainFindFragment.TYPE_CODE_LOAD) {
				this.adapter.loadList(list);
			} else if (type == MainFindFragment.TYPE_CODE_MORE) {
				this.adapter.appendList(list);
			}
		}
		super.onPostExecute(result);
	}

}
