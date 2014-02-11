package com.ipet.android.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ipet.R;
import com.ipet.android.task.FindLoadAsyncTask;
import com.ipet.android.ui.adapter.FindGridAdapter;
import com.ipet.android.vo.Feed;

public class MainFindFragment extends Fragment {
	private Activity activity;
	private GridView gridview;
	private final List<Feed> list = new ArrayList<Feed>(0);
	private FindGridAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_tab_find, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.activity = getActivity();
		gridview = (GridView) this.activity.findViewById(R.id.gridview);
		adapter = new FindGridAdapter(this.activity);
		adapter.setList(list);
		gridview.setAdapter(adapter);

		// list = FeedManager.load();
		// adapter.appendList(list);

		new FindLoadAsyncTask(gridview, adapter).execute();
	}
}
