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
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.task.FindLoadAsyncTask;
import com.ipet.android.ui.adapter.FindGridAdapter;
import com.ipet.android.ui.utils.DateTimeUtils;

public class MainFindFragment extends Fragment {
	public static final int TYPE_CODE_LOAD = 10;
	public static final int TYPE_CODE_MORE = 20;
	public static final int LIST_SIZE = 24;
	private Activity activity;
	private GridView gridview;
	private final List<IpetPhoto> list = new ArrayList<IpetPhoto>(0);
	private FindGridAdapter adapter;
	private String timeline;
	private int page;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_tab_find, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.activity = getActivity();
		gridview = (GridView) this.activity.findViewById(R.id.gridview);
		adapter = new FindGridAdapter(this.activity,gridview);
		adapter.setList(list);
		gridview.setAdapter(adapter);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		timeline = DateTimeUtils.getNowDateTime();
		page = 0;
		new FindLoadAsyncTask(this, gridview, adapter, MainFindFragment.TYPE_CODE_LOAD).execute(timeline, String.valueOf(page));
		super.onStart();
	}

}
