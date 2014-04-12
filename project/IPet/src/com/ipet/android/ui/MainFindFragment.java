package com.ipet.android.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.ipet.R;
import com.ipet.android.Constant;
import com.ipet.android.sdk.domain.IpetComment;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.task.FindLoadAsyncTask;
import com.ipet.android.ui.adapter.FindGridAdapter;
import com.ipet.android.ui.utils.DateTimeUtils;

public class MainFindFragment extends Fragment {
	public static final int TYPE_CODE_LOAD = 10;
	public static final int TYPE_CODE_MORE = 20;
	public static final int VIEW_IPET_PHOTO = 100;

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
		Log.i("MainFindFragment", "onActivityCreated");
		this.activity = getActivity();
		gridview = (GridView) this.activity.findViewById(R.id.gridview);
		adapter = new FindGridAdapter(this.activity, gridview);
		adapter.setList(list);
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(myclick);

	}

	private BroadcastReceiver broadcastreciver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED.equals(action)) {
				IpetPhoto ipetPhoto = (IpetPhoto) intent.getSerializableExtra(Constant.IPET_PHOTO_SERIALIZABLE);
				MainFindFragment.this.updateLike(ipetPhoto);
			}

			if (Constant.BROADCAST_INTENT_IPET_PHOTO_COMMENT.equals(action)) {
				IpetComment comment = (IpetComment) intent.getSerializableExtra(Constant.IPET_COMMENT_SERIALIZABLE);
				String type = (String) intent.getStringExtra(Constant.IPET_COMMENT_TYPE);
				MainFindFragment.this.updateComment(type, comment);
			}
		}

	};

	private OnItemClickListener myclick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			IpetPhoto feed = adapter.getItem(position);
			Intent intent = new Intent(MainFindFragment.this.activity, PhotoViewActivity.class);
			Bundle mBundle = new Bundle();
			mBundle.putSerializable(Constant.IPET_PHOTO_SERIALIZABLE, (Serializable) feed);
			intent.putExtras(mBundle);
			startActivity(intent);
		}

	};

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		this.activity.unregisterReceiver(broadcastreciver);
	}

	protected void updateComment(String type, IpetComment comment) {
		// TODO Auto-generated method stub
		this.adapter.updateComment(type, comment);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.BROADCAST_INTENT_IPET_PHOTO_FAVORED);
		this.activity.registerReceiver(broadcastreciver, filter);

		Log.i("MainFindFragment", "onStart");
		timeline = DateTimeUtils.getNowDateTime();
		page = 0;
		new FindLoadAsyncTask(this, gridview, adapter, MainFindFragment.TYPE_CODE_LOAD).execute(timeline, String.valueOf(page));
		super.onStart();

	}

	public void updateLike(IpetPhoto ipetPhoto) {
		this.adapter.updateLike(ipetPhoto);
	}

}
