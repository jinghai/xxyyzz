package com.ipet.android.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ipet.R;
import com.ipet.android.task.FeedLoadAsyncTask;
import com.ipet.android.task.FeedLoadMoreAsyncTask;
import com.ipet.android.task.FeedLoadNewAsyncTask;
import com.ipet.android.ui.adapter.ListFeedAdapter;
import com.ipet.android.ui.common.FeedListView;
import com.ipet.android.ui.common.FeedListView.OnLoadMoreListener;
import com.ipet.android.vo.Feed;
import com.ipet.android.widget.PullToRefreshListView.OnRefreshListener;

public class MainHomeFragment extends Fragment {
	private Activity activity;
	private final ArrayList<Feed> list = new ArrayList<Feed>(0);
	private FeedListView listView;
	private ListFeedAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_tab_home, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		this.activity = getActivity();

		listView = (FeedListView) activity.findViewById(R.id.main_home_listView);
		adapter = new ListFeedAdapter(activity, list);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(adapter);
		listView.setPinnedHeaderView(activity.getLayoutInflater().inflate(R.layout.list_feed_item_header, listView, false));

		View emptyView = activity.findViewById(R.id.empty);
		listView.setEmptyView(emptyView);

		new FeedLoadAsyncTask(listView, adapter).execute();

		listView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Do work to refresh the list here.
				new FeedLoadNewAsyncTask(listView, adapter).execute();
			}
		});

		listView.setOnLoadMoreListener(new OnLoadMoreListener() {

			@Override
			public void LoadMore() {
				// TODO Auto-generated method stub
				new FeedLoadMoreAsyncTask(listView, adapter).execute();
			}

		});

	}

}
